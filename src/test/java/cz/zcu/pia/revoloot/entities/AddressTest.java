package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.web.FormConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {

    private Address address;
    private Set<String> allErrors;

    @BeforeEach
    void setUp(){
        address = new Address();
        allErrors = new HashSet<>();
        allErrors.add(FormConfig.POSTAL_CODE);
        allErrors.add(FormConfig.CITY);
        allErrors.add(FormConfig.HOUSE_NUMBER);
        allErrors.add(FormConfig.STREET);
    }

    @Test
    void street() {
        String str = "TestVal";
        address.setStreet(str);
        assertEquals(str, address.getStreet(), "Street setter problem");
    }

    @Test
    void houseNo() {
        String str = "65E";
        address.setHouseNo(str);
        assertEquals(str, address.getHouseNo(), "HouseNo setter problem");
    }

    @Test
    void city() {
        String str = "TestVal";
        address.setCity(str);
        assertEquals(str, address.getCity(), "City setter problem");
    }

    @Test
    void postalCode() {
        int val = 31200;
        address.setPostalCode(val);
        assertEquals(val, address.getPostalCode(), "Postal code setter problem");
    }

    @Test
    void validateStreet() {
        address.setStreet("Street");

        allErrors.remove(FormConfig.STREET);

        assertEquals(allErrors, address.validate());
    }

    @Test
    void validateHouseNo() {
        address.setHouseNo("65E");

        allErrors.remove(FormConfig.HOUSE_NUMBER);

        assertEquals(allErrors, address.validate());
    }

    @Test
    void validateCity() {
        address.setCity("City");

        allErrors.remove(FormConfig.CITY);

        assertEquals(allErrors, address.validate());
    }

    @Test
    void validatePostalCode() {
        address.setPostalCode(31200);
        allErrors.remove(FormConfig.POSTAL_CODE);

        assertEquals(allErrors, address.validate());
    }

    @Test
    void validate() {
        assertEquals(allErrors, address.validate());
    }
}