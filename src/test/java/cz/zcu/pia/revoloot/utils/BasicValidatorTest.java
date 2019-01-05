package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicValidatorTest {

    private IValidator validator;

    @BeforeEach
    void setUp() {
        validator = new BasicValidator();
    }

    @Test
    void checkBirthAgainstPersonIDMale() throws ParseException {
        String date = "01-11-1993";
        long personID = 9311012139L;
        Date regform = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        boolean result = validator.checkBirthAgainstPersonID(regform, Gender.MALE, personID);
        assertTrue(result);
    }

    @Test
    void checkBirthAgainstPersonIDFemale() throws ParseException {
        String date = "01-11-1993";
        long personID = 9361012139L;
        Date regform = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        boolean result = validator.checkBirthAgainstPersonID(regform, Gender.FEMALE, personID);
        assertTrue(result);
    }

    @Test
    void checkBirthAgainstPersonIDFalse1() throws ParseException {
        String date = "11-11-1993";
        long personID = 9361012139L;
        Date regform = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        boolean result = validator.checkBirthAgainstPersonID(regform, Gender.FEMALE, personID);
        assertFalse(result);
    }

    @Test
    void checkBirthAgainstPersonIDFalse2() throws ParseException {
        String date = "01-11-1993";
        long personID = 9361012139L;
        Date regform = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        boolean result = validator.checkBirthAgainstPersonID(regform, Gender.MALE, personID);
        assertFalse(result);
    }


}