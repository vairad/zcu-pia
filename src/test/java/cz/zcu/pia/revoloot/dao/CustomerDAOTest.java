package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.EntityFactory;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerDAOTest extends DaoTest {


    private static CustomerDAO customerDAO;
    private static IEncoder encoder;

    // region preprare tests
    @BeforeAll
    static void setUpDependencies() {
        encoder = new PasswordHashEncoder();
        customerDAO = new CustomerDAO(em);

        for (int order = 0; order < 10; order++) {
            customerDAO.save(EntityFactory.createCustomer("login"+order));
        }
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
        Customer customer = EntityFactory.createCustomer();
        customer = customerDAO.save(customer);

        assertNotEquals(customer.getId(), 0L, "Id was not returned.");
    }

    @Test
    void saveMultiple() {
        try {
            Customer customer = EntityFactory.createCustomer();
            customer = customerDAO.save(customer);

            Customer customer2 = EntityFactory.createCustomer();
            customer2 = customerDAO.save(customer2);
            fail();
        }catch (PersistenceException ex){
            assertTrue(true);
        }
    }

    @Test
    void findOne() {
    }

    @Test
    void remove() {
    }
}