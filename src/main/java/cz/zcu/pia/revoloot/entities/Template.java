package cz.zcu.pia.revoloot.entities;

public class Template extends BaseEntity {

    String name;

    User author;

    long amount;
    Currency currency;

    int variableSymbol;
    int constantSymbol;
    int specificSymbol;

    String message;
    String note;
}
