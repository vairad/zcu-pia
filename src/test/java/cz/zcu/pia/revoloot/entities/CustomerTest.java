package cz.zcu.pia.revoloot.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void setPersonIDSmart() {
        Customer customer = new Customer();
        customer.setPersonIDSmart("931101/2139");
        assertEquals(9311012139L, (long)customer.getPersonID());
    }

    @Test
    void setPersonIDSmart2() {
        Customer customer = new Customer();
        customer.setPersonIDSmart("9311012139");
        assertEquals(9311012139L, (long)customer.getPersonID());
    }
}