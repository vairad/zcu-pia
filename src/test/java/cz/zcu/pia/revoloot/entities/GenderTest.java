package cz.zcu.pia.revoloot.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void isMALE() {
        assertTrue(Gender.MALE.isMALE());
        assertFalse(Gender.MALE.isFEMALE());
    }

    @Test
    void isFEMALE() {
        assertTrue(Gender.FEMALE.isFEMALE());
        assertFalse(Gender.FEMALE.isMALE());
    }

    @Test
    void fromStringMale() {
        String gender = "MaLe";
        Gender resolved = Gender.fromString(gender);
        assertEquals(Gender.MALE, resolved);
    }


    @Test
    void fromStringFemale() {
        String gender = "fEMale";
        Gender resolved = Gender.fromString(gender);
        assertEquals(Gender.FEMALE, resolved);
    }

    @Test()
    void fromString() {
        String gender = "marek";
        try {
            Gender resolved = Gender.fromString(gender);
            fail();
        }catch (IllegalArgumentException ex){
            assertTrue(true);
        }
    }
}