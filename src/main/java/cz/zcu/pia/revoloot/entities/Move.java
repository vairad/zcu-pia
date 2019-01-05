package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = TableConfig.TABLE_MOVES)
public class Move extends BaseEntity implements IValidable {

    private Account owner;

    private AccountAddress source;
    private AccountAddress destination;

    private long amount;
    private Currency currency;

    private int variableSymbol;
    private int constantSymbol;
    private int specificSymbol;

    private Date submissionDate;
    private Date transferDate;

    private String message;
    private String note;

    private boolean processed;

    @ManyToOne
    @JoinColumn(name = "owner")
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="prepend", column = @Column(name="src_prepend") ),
            @AttributeOverride(name="number", column = @Column(name="src_number") ),
            @AttributeOverride(name="bankCode", column = @Column(name="src_bankCode") )
    } )
    public AccountAddress getSource() {
        return source;
    }

    public void setSource(AccountAddress source) {
        this.source = source;
    }

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="prepend", column = @Column(name="dst_prepend") ),
            @AttributeOverride(name="number", column = @Column(name="dst_number") ),
            @AttributeOverride(name="bankCode", column = @Column(name="dst_bankCode") )
    } )
    public AccountAddress getDestination() {
        return destination;
    }

    public void setDestination(AccountAddress destination) {
        this.destination = destination;
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

    public int getVariableSymbol() {
        return variableSymbol;
    }

    public void setVariableSymbol(int variableSymbol) {
        this.variableSymbol = variableSymbol;
    }

    public int getConstantSymbol() {
        return constantSymbol;
    }

    public void setConstantSymbol(int constantSymbol) {
        this.constantSymbol = constantSymbol;
    }

    public int getSpecificSymbol() {
        return specificSymbol;
    }

    public void setSpecificSymbol(int specificSymbol) {
        this.specificSymbol = specificSymbol;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>();
        //todo validation
        return errors;
    }
}
