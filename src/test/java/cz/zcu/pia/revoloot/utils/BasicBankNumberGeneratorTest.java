package cz.zcu.pia.revoloot.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicBankNumberGeneratorTest {

    private IBankNumbers bankNumbers = new BasicBankNumberGenerator();

    @Test
    void getBankCode() {
        long newNum = bankNumbers.getBankCode();
        assertEquals(3666, newNum);
    }

    @Test
    void getNewAccNum() {
        long newNum = bankNumbers.getNewAccNum(0L);
        assertEquals(30606161L, newNum);
    }


    @Test
    void getNewAccNum2() {
        long newNum = bankNumbers.getNewAccNum(30616261L);
        assertEquals(30616362L, newNum);
    }

    @Test
    void getNewAccNum3() {
        long newNum = bankNumbers.getNewAccNum(31636361L);
        assertEquals(31636462L, newNum);
    }

    @Test
    void getNewAccNum4() {
        long newNum = bankNumbers.getNewAccNum(134666461L);
        assertEquals(134666562L, newNum);
    }

    @Test
    void validateNumber() {
        assertTrue(bankNumbers.validateNumber(134666562L));
    }
    @Test
    void validateNumber2() {
        assertTrue(bankNumbers.validateNumber(31636462L));
    }
    @Test
    void validateNumber3() {
        assertTrue(bankNumbers.validateNumber(30616362L));
    }
    @Test
    void validateNumber4() {
        assertTrue(bankNumbers.validateNumber(30606161L));
    }

    @Test
    void validateNumber5() {
        assertFalse(bankNumbers.validateNumber(30616161L));
    }
}