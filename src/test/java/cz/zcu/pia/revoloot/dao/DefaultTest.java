package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.dao.db.AccountDAO;
import cz.zcu.pia.revoloot.dao.db.BankerDAO;
import cz.zcu.pia.revoloot.dao.db.CustomerDAO;
import cz.zcu.pia.revoloot.dao.db.MoveDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.IPasswordGenerator;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static cz.zcu.pia.revoloot.entities.EntityFactory.createAccountInfo;
import static cz.zcu.pia.revoloot.entities.EntityFactory.createMove;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DefaultTest extends DaoTest {
    private static CustomerDAO customerDAO;
    private static AccountDAO accountDAO;
    private static MoveDAO moveDAO;
    private static BankerDAO bankerDAO;

    private static IEncoder encoder;
    private static IPasswordGenerator generator;


    static Customer prepareCustomer(String login, String password) {
        Address address = new Address();
        address.setCity("Plzeň");
        address.setHouseNo("15E");
        address.setStreet("Mulajova");
        address.setPostalCode(31250);
        address.setState(State.SVK);

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress(address);
        contactInfo.setEmail("boo@voo.doo");
        contactInfo.setPhone(123456789);

        Customer customer = new Customer();
        customer.setCreated(new Date());
        customer.setLogin(login);
        customer.setPassword(encoder.encode(password));
        customer.setName("Marek");
        customer.setSurname("Prijmenak");
        customer.setContactInfo(contactInfo);
        customer.setGender(Gender.MALE);
        customer.setCardID("a15888OP");

        return customer;
    }

    static Banker prepareBanker(String login, String image) {
        Address address = new Address();
        address.setCity("Plzeň");
        address.setHouseNo("15c");
        address.setStreet("Bukačova");
        address.setPostalCode(31250);
        address.setState(State.CZ);


        Banker banker = new Banker();
        banker.setBranch(address);
        banker.setLogin(login);
        banker.setPassword(encoder.encode("pass"));
        banker.setName("Marek");
        banker.setSurname("Banker");
        banker.setPhoto(image);
        banker.setEmail(login + "@revoloot.cz");
        banker.setGender(Gender.FEMALE);

        return banker;
    }

    // region preprare tests
    @BeforeAll
    static void setUpDependencies() {
        encoder = new PasswordHashEncoder();
        customerDAO = new CustomerDAO(em);
        accountDAO = new AccountDAO(em);
        moveDAO = new MoveDAO(em);
        bankerDAO = new BankerDAO(em);
    }


    @Test
    void save() {
        Customer customer = EntityFactory.createCustomer();
        customer.setLogin("test");
        customer.setPassword(encoder.encode("pass"));
        customer = customerDAO.save(customer);

        assertNotEquals(customer.getId(), 0L, "Id was not returned.");

        Account a = new Account();
        a.setCustomer(customer);
        a.setAccountInfo(createAccountInfo());
        a.setAmount(50000);

        accountDAO.save(a);

        assertNotEquals(a.getId(), 0L, "Id was not returned.");

        Move m = createMove();
        m.setOwner(a);
        moveDAO.save(m);

        assertNotEquals(m.getId(), 0L, "Id was not returned.");

        Banker banker = prepareBanker("bank1", "banker1.png");
        banker = bankerDAO.save(banker);
        assertNotEquals(banker.getId(), 0L, "Id was not returned.");

        Banker banker2 = prepareBanker("bank2", "banker2.png");
        banker2 = bankerDAO.save(banker2);
        assertNotEquals(banker2.getId(), 0L, "Id was not returned.");
    }

}
