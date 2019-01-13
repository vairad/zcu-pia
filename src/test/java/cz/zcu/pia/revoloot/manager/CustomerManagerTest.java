package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.db.AccountDAO;
import cz.zcu.pia.revoloot.dao.db.CustomerDAO;
import cz.zcu.pia.revoloot.dao.db.UserDAO;
import cz.zcu.pia.revoloot.entities.ContactInfo;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.EntityFactory;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.utils.BasicPasswordGenerator;
import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerManagerTest extends ManagerBaseTest {

    private CustomerManager customerManager;

    @BeforeEach
    void setUp() {
        setUpConnection();
        customerManager = new CustomerManager( new BasicValidator()
                , new CustomerDAO(em), new BasicPasswordGenerator()
                , new UserDAO(em, new PasswordHashEncoder()), new AccountDAO(em), null, new PasswordHashEncoder() );
    }

    @AfterEach
    void tearDown(){
        endConnection();
    }

    @Test
    void register() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);
        assertNotEquals(0L, customer);
    }

    @Test
    void getByUsername() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);

        Customer find = customerManager.getByUsername(customer.getLogin());
        assertEquals(customer, find);
    }

    @Test
    void getCustomerByRBI() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);

        Customer find = customerManager.getCustomerByRBI(customer.getRBI());
        assertEquals(customer, find);
    }

    @Test
    void updateCustomerInfo() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);

        ContactInfo oldInfo = customer.getContactInfo();
        ContactInfo newInfo = EntityFactory.createContactInfo();
        newInfo.setEmail("another@email.com");

        customerManager.updateCustomerInfo(true, newInfo, customer);
        assertEquals(customer.getContactInfo(), newInfo);
        assertNotEquals(customer.getContactInfo(), oldInfo);
    }

    @Test
    void updateCustomer() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);

        Customer changes = EntityFactory.createCustomer();
        changes.setName("Novyjmeno");

        assertNotEquals(changes, customer);

        customerManager.updateCustomer(true, changes, customer, customer);

        assertEquals(changes, customer); //hibernate save make both instances same during save
        assertEquals(changes.getId(), customer.getId());
        assertEquals(changes.getLogin(), customer.getLogin());
        assertEquals(changes.getPassword(), customer.getPassword());
    }

    @Test
    void removeCustomer() throws CustomerValidationException {
        Customer customer = EntityFactory.createCustomer();
        customerManager.register(true, customer);
        Customer customer2 = EntityFactory.createCustomer();
        customerManager.register(true, customer);

        customerManager.removeCustomer(customer, customer2);

        Customer find = customerManager.getByUsername(customer.getLogin());
        assertNull(find);
    }
}