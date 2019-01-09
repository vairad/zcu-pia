package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Gender;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BasicValidator implements IValidator {

    @Override
    public boolean isEmptyField(String field) {
        return (field == null || field.isEmpty());
    }

    @Override
    public boolean isEmptyField(Date field) {
        return field == null;
    }

    @Override
    public boolean isEmptyField(Long field) {
        return (field == null || field == 0);
    }

    @Override
    public boolean isEmptyField(Integer field) {
        return (field == null || field == 0);
    }

    @Override
    public boolean isEmptyField(Double field) {
        return (field == null || field == 0);
    }

    @Override
    public boolean checkBirthAgainstPersonID(Date birthDate, Gender gender, long personID) {
        if(gender == Gender.FEMALE){
            personID -= 50 * 1000000;
        }
        Date computedDate;
        try {
            computedDate = new SimpleDateFormat("yyMMdd").parse(Long.toString(personID).substring(0,6));
        } catch (ParseException e) {
            return false;
        }

        return birthDate.equals(computedDate);
    }
}
