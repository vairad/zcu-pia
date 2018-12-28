package cz.zcu.pia.revoloot.entities;

import java.util.Date;

public class Move extends BaseEntity {

    Account source;
    Account destination;

    long amount;
    Currency currency;

    int variableSymbol;
    int constantSymbol;
    int specificSymbol;

    Date submissionDate;
    Date transferDate;

    String message;
    String note;

    boolean processed;
}
