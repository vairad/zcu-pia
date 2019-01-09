package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = TableConfig.TABLE_MOVES)
public class Move extends BaseEntity implements IValidable {

    private Account owner;

    private AccountAddress source;
    private AccountAddress destination;

    private Double amount;
    private Currency currency;

    private Integer variableSymbol;
    private Integer constantSymbol;
    private Integer specificSymbol;

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
    @AttributeOverrides({
            @AttributeOverride(name = "prepend", column = @Column(name = "src_prepend")),
            @AttributeOverride(name = "number", column = @Column(name = "src_number")),
            @AttributeOverride(name = "bankCode", column = @Column(name = "src_bankCode"))
    })
    public AccountAddress getSource() {
        return source;
    }

    public void setSource(AccountAddress source) {
        this.source = source;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "prepend", column = @Column(name = "dst_prepend")),
            @AttributeOverride(name = "number", column = @Column(name = "dst_number")),
            @AttributeOverride(name = "bankCode", column = @Column(name = "dst_bankCode"))
    })
    public AccountAddress getDestination() {
        return destination;
    }

    public void setDestination(AccountAddress destination) {
        this.destination = destination;
    }

    @Column(nullable = false)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column
    public Integer getVariableSymbol() {
        return variableSymbol;
    }

    public void setVariableSymbol(Integer variableSymbol) {
        this.variableSymbol = variableSymbol;
    }

    @Column
    public Integer getConstantSymbol() {
        return constantSymbol;
    }

    public void setConstantSymbol(Integer constantSymbol) {
        this.constantSymbol = constantSymbol;
    }

    @Column
    public Integer getSpecificSymbol() {
        return specificSymbol;
    }

    public void setSpecificSymbol(Integer specificSymbol) {
        this.specificSymbol = specificSymbol;
    }

    /**
     * Datum splatnosti
     *
     * @return datum požadovné splatnosti
     */
    @Column(nullable = false)
    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * Datum zúčtování transakce
     *
     * @return datum zaúčtování ransakce
     */
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

        if (validator.isEmptyField(amount)) {
            errors.add(FormConfig.AMOUNT);
        }
        if (destination == null) {
            errors.addAll(new AccountAddress().errorFields());
        } else {
            errors.addAll(destination.validate(validator));
        }
        if (validator.isEmptyField(submissionDate)) {
            errors.add(FormConfig.DUE_DATE);
        }
        if (currency == null) {
            errors.add(FormConfig.CURRENCY);
        }
        return errors;
    }

    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>();
        errors.add(FormConfig.AMOUNT);
        errors.add(FormConfig.DUE_DATE);
        errors.add(FormConfig.ACC_NUM);
        errors.add(FormConfig.BANK_CODE);
        errors.add(FormConfig.CURRENCY);

        return errors;
    }
}
