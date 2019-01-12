package cz.zcu.pia.revoloot.manager;

public class AccountCreateException extends Exception {

    private String errors;

    public AccountCreateException(String errors) {
        this.errors = errors;
    }

    public String getErrors(){
        return errors;
    }
}
