package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountAddressTest {

    IValidator validator = new BasicValidator();

    @Test
    void testToStringPrepend() {
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setBankCode(3666);
        accountAddress.setNumber(222L);
        accountAddress.setPrepend(111L);

        assertEquals("111-222/3666", accountAddress.toString());
    }

    @Test
    void testToStringNoPrepend() {
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setBankCode(3666);
        accountAddress.setNumber(222L);

        assertEquals("222/3666", accountAddress.toString());
    }


    @Test
    void validate() {
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setBankCode(3666);
        accountAddress.setNumber(222L);
        accountAddress.setNumber(111L);

        assertTrue(accountAddress.validate(validator).isEmpty());
    }

    @Test
    void validate2() {
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setBankCode(3666);
        accountAddress.setNumber(222L);

        assertTrue(accountAddress.validate(validator).isEmpty());
    }


    @Test
    void validateAllErrors() {
        AccountAddress accountAddress = new AccountAddress();

        Set<String> validated = accountAddress.validate(validator);
        Set<String> expected = accountAddress.errorFields();
        assertEquals(expected, validated);
    }
}