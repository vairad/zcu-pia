package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DefaultTest {
    private static final String PERSISTENCE_UNIT = "cz.zcu.pia.revoloot.test";
    private static EntityManager em;
    private static CustomerDAO customerDAO;
    private static AccountDAO accountDAO;
    private static MoveDAO moveDAO;
    private static IEncoder encoder;


    static Customer prepareCustomer(String login, String password) {
        Address address = new Address();
        address.setCity("Autommat");
        address.setHouseNo("15");
        address.setStreet("Mulajova");
        address.setPostalCode(31250);

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

        return customer;
    }

    // region preprare tests
    @BeforeAll
    static void setUpConnection() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = factory.createEntityManager();
        encoder = new PasswordHashEncoder();
        customerDAO = new CustomerDAO(em);
        accountDAO = new AccountDAO(em);
        moveDAO = new MoveDAO(em);
    }

    @BeforeEach
    void beginTxn() {
        em.getTransaction().begin();
    }

    @AfterEach
    void endTxn() {
        em.getTransaction().commit();
    }

    @AfterAll
    static void tearDownConnection() {
        em.close();
    }


    @Test
    void save() {
        Customer customer = prepareCustomer("test", "pass");
        customer = customerDAO.save(customer);

        assertNotEquals(customer.getId(), 0L, "Id was not returned.");

        AccountAddress accounInfo = new AccountAddress();
        accounInfo.setNumber(222);
        accounInfo.setBankCode(3666);

        Account a = new Account();
        a.setCustomer(customer);
        a.setAccountInfo(accounInfo);
        a.setAmount(50000);

        accountDAO.save(a);

        assertNotEquals(a.getId(), 0L, "Id was not returned.");

        Move m = new Move();
        m.setAmount(500);
        m.setConstantSymbol(666);
        m.setDestination(accounInfo);
        m.setSource(accounInfo);
        m.setMessage("Messsage");
        m.setNote("To byl ale dement!");
        m.setSubmissionDate(new Date());
        m.setOwner(a);

        moveDAO.save(m);

        assertNotEquals(m.getId(), 0L, "Id was not returned.");
    }

}
