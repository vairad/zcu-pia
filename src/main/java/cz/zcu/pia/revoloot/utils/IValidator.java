package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Gender;

import java.util.Date;

/**
 * Rozhraní pro objekt validující hodnoty.
 *
 */
public interface IValidator {

    boolean isEmptyField(String field);
    boolean isEmptyField(Date date);
    boolean checkBirthAgainstPersonID(Date birthDate, Gender gender, long personID);

}
