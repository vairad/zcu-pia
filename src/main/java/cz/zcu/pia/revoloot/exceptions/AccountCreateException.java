package cz.zcu.pia.revoloot.exceptions;

public class AccountCreateException extends Exception implements IValidationException {

    private String errors;

    public AccountCreateException(String errors) {
        this.errors = errors;
    }

    public String getErrors(){
        return errors;
    }
}
