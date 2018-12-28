package cz.zcu.pia.revoloot.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TableConfig.TABLE_ACCOUNTS)
public class Account extends BaseEntity {

    long preNumber;
    long accountNumber;
    long amount;

    Currency currency;
}
