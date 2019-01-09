package cz.zcu.pia.revoloot.entities.exceptions;

import java.util.Arrays;
import java.util.Set;

public class MoveValidationException extends Exception implements IValidationException {

    private Set<String> errors;

    public MoveValidationException(Set<String> errors) {
        super("Move object has incorrect fields");
        this.errors = errors;
    }

    @Override
    public String getErrors() {
        return Arrays.toString(errors.toArray());
    }
}
