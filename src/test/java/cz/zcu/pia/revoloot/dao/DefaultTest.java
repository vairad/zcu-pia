package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.dao.db.*;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.IPasswordGenerator;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static cz.zcu.pia.revoloot.entities.EntityFactory.createAccountInfo;
import static cz.zcu.pia.revoloot.entities.EntityFactory.createExchangeRate;
import static cz.zcu.pia.revoloot.entities.EntityFactory.createMove;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DefaultTest extends DaoTest {
    private static ICustomerDAO customerDAO;
    private static IAccountDAO accountDAO;
    private static IMoveDAO moveDAO;
    private static IBankerDAO bankerDAO;
    private static IExchangeDAO exchangeDAO;

    private static IEncoder encoder;
    private static IPasswordGenerator generator;


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
        exchangeDAO = new ExchangeDAO(em);
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
        a.setAmount(50000.00);

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

        ExchangeRate ex = createExchangeRate(Currency.CZK, Currency.GBP, 0.0351080553);
        exchangeDAO.save(ex);
        ex = createExchangeRate(Currency.GBP, Currency.CZK, 28.4834916);
        exchangeDAO.save(ex);
    }

}
