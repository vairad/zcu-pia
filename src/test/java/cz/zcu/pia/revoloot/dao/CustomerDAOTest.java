package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Address;
import cz.zcu.pia.revoloot.entities.ContactInfo;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    private static final String PERSISTENCE_UNIT = "cz.zcu.pia.revoloot.test";
    private static EntityManager em;
    private static CustomerDAO customerDAO;
    private static IEncoder encoder;


    static Customer prepareCustomer(String login, String password) {
        Address address = new Address();
        address.setCity("Plzeň");
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

        for (int order = 0; order < 10; order++) {
            customerDAO.save(prepareCustomer("login" + order, "pass" + order));
        }
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
    void findByAccountNumber() {
    }

    @Test
    void findByCustomerId() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void authenticate() {
    }

    @Test
    void save() {
        Customer customer = prepareCustomer("test", "pass");
        customer = customerDAO.save(customer);

        assertNotEquals(customer.getId(), 0L, "Id was not returned.");
    }

    @Test
    void findOne() {
    }

    @Test
    void remove() {
    }
}