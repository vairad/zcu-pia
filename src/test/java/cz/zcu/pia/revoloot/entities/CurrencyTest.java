package cz.zcu.pia.revoloot.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void fromStringCZK() {
        Currency c = Currency.fromString("cZk");
        assertEquals(Currency.CZK, c);
    }

    @Test
    void fromStringGBP() {
        Currency c = Currency.fromString("GbP");
        assertEquals(Currency.GBP, c);
    }


    @Test
    void fromStringEUR() {
        Currency c = Currency.fromString("euR");
        assertEquals(Currency.EUR, c);
    }
}