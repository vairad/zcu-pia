package cz.zcu.pia.revoloot.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = TableConfig.TABLE_ACCOUNTS)
public class Account extends BaseEntity {

    @Embedded
    AccountAddress accountInfo;

    long amount;

    List<Move> moves;

    Currency currency;

    Customer customer;

    public AccountAddress getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountAddress accountInfo) {
        this.accountInfo = accountInfo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    @ManyToOne
    @JoinColumn(name = "customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @OneToMany
    @JoinColumn(name = "id")
    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
