package cz.zcu.pia.revoloot.entities;

import javax.persistence.*;

@Entity
@Table(name = TableConfig.TABLE_TEMPLATES)
public class Template extends BaseEntity {

    private String name;
    private Customer owner;

    private Account source;
    private AccountAddress destination;

    private Double amount;
    private Currency currency;

    private Integer variableSymbol;
    private Integer constantSymbol;
    private Integer specificSymbol;

    private String message;
    private String note;

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Account getSource() {
        return source;
    }

    public void setSource(Account owner) {
        this.source = owner;
    }

    @ManyToOne
    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Column
    public AccountAddress getDestination() {
        return destination;
    }

    public void setDestination(AccountAddress destination) {
        this.destination = destination;
    }

    @Column
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column
    @Enumerated(value = EnumType.STRING)
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

    @Column
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Transient
    public Move getMove() {
        Move move = new Move();
        move.setOwner(source);
        move.setDestination(destination);
        move.setAmount(amount);
        move.setCurrency(currency);
        move.setVariableSymbol(variableSymbol);
        move.setConstantSymbol(constantSymbol);
        move.setSpecificSymbol(specificSymbol);
        move.setMessage(message);
        move.setNote(note);
        return move;
    }

    public void setMove(Move move) {
        source = move.getOwner();
        destination = move.getDestination();
        amount = move.getAmount();
        currency = move.getCurrency();
        variableSymbol = move.getVariableSymbol();
        constantSymbol = move.getConstantSymbol();
        specificSymbol = move.getSpecificSymbol();
        message = move.getMessage();
        note = move.getNote();
    }
}
