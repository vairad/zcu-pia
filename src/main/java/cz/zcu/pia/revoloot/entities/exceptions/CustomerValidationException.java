package cz.zcu.pia.revoloot.entities.exceptions;

import java.util.Arrays;
import java.util.Set;

public class CustomerValidationException extends Exception implements IValidationException {

    private Set<String> errors;

    public CustomerValidationException(Set<String> errors) {
        super("Customer object has incorrect fields");
        this.errors = errors;
    }

    @Override
    public String getErrors() {
        return Arrays.toString(errors.toArray());
    }

}
