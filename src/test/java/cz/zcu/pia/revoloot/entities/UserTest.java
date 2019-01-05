package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static IValidator validator;

    @BeforeAll
    static void preparation(){
        validator = new BasicValidator();
    }

    @Test
    void validateErrors(){
        User user = new User();
        Set<String> errorsV = user.validate(validator);
        Set<String> errorsAll = user.errorFields();
        assertEquals(errorsAll, errorsV, "Validation do not contains all fields");
    }
}