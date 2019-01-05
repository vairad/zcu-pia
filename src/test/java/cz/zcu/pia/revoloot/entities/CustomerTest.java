package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private static IValidator validator;
    private Customer c;

    @BeforeAll
    static void preparation() {
        validator = new BasicValidator();
    }

    @BeforeEach
    void setUp() {
        c = EntityFactory.createCustomer();
    }

    @Test
    void setPersonIDSmart() {
        Customer customer = new Customer();
        customer.setPersonIDSmart("931101/2139");
        assertEquals(9311012139L, (long) customer.getPersonID());
    }

    @Test
    void setPersonIDSmart2() {
        Customer customer = new Customer();
        customer.setPersonIDSmart("9311012139");
        assertEquals(9311012139L, (long) customer.getPersonID());
    }

    @Test
    void validateContactInfoNull() {
        c.setContactInfo(null);
        Set<String> errors = c.validate(validator);
        boolean result = errors.containsAll(new ContactInfo().errorFields());

        assertTrue(result);
    }

    @Test
    void validateContactInfoOK() {
        Set<String> errors = c.validate(validator);
        boolean result = false;
        for (String error : new ContactInfo().errorFields()) {
            result |= errors.contains(error);
        }

        assertFalse(result);
    }

    @Test
    void validateContactInfoFail() {

    }

    @Test
    void validateBirthDate() {

    }

    @Test
    void validatePersonID() {

    }

    @Test
    void validateCardID() {

    }

    @Test
    void validateErrors() {
        Customer c = new Customer();
        Set<String> errorsV = c.validate(validator);
        Set<String> errorsAll = c.errorFields();
        assertEquals(errorsAll, errorsV, "Validation do not contains all fields");
    }
}