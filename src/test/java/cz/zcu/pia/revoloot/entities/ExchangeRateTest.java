package cz.zcu.pia.revoloot.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateTest {

    @Test
    void testToString() {
        ExchangeRate ex = new ExchangeRate();
        ex.setFromCur(Currency.CZK);
        ex.setToCur(Currency.GBP);
        ex.setRate(15.56);

        assertEquals("CZK->GBP = 15.56", ex.toString());
    }
}