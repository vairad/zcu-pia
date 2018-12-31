package cz.zcu.pia.revoloot.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Entitní objekt účtu v systému
 *
 * @author Radek Vais
 */
@Entity
@Table(name = TableConfig.TABLE_ACCOUNTS)
public class Account extends BaseEntity {

    /**
     * číslo účtu
     */
    private AccountAddress accountInfo;

    /**
     * měna účtu
     */
    private Currency currency;
    /**
     * disponibilní zůstatek
     */
    private double amount;
    /**
     * účetní zůstatek
     */
    private double trueAmount;

    /**
     * seznam pohybů na účtu
     */
    private List<Move> moves;
    /**
     * majitel účtu
     */
    private Customer customer;
    /**
     * příznak blokování všech operací nad účtem
     */
    private boolean blocked;

    /**
     * Konstruktor objektu nastaví výchozí hodnoty nového účtu:
     * amount = trueAmount = 0
     * currency = CZK
     * blcked = false
     */
    public Account() {
        this.blocked = false;
        this.currency = Currency.CZK;
        this.amount = this.trueAmount = 0.0;
    }

    /**
     * Vrací informace o čísle účtu.
     * Pole nelze upravit po prvním zapsání do databáze.
     *
     * @return číslo účtu
     */
    @Column(updatable = false)
    @Embedded
    public AccountAddress getAccountInfo() {
        return accountInfo;
    }

    /**
     * Nastavuje informace o čísle účtu.
     *
     * @param accountInfo objekt čísla účtu
     */
    public void setAccountInfo(AccountAddress accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * Disponibilní zůstatek účtu - používán k ověření možnosti provést platbu.
     *
     * @return disponibilní zůstatek účtu.
     */
    @Column
    public double getAmount() {
        return amount;
    }

    /**
     * Nastaví disponinilní zůstatek účtu.
     * Operace je idempotentní.
     *
     * @param amount aktuální stav konta.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Účetní zůstatek - používán pro zpracování operací.
     *
     * @return účetní zůstatek účtu
     */
    @Column
    public double getTrueAmount() {
        return trueAmount;
    }

    /**
     * Nastaví disponinilní zůstatek účtu.
     * Operace je idempotentní.
     * V aplikaci používat jen po zaúčtování transakce
     *
     * @param trueAmount aktuální hodnota disponibilního zůstatku
     */
    public void setTrueAmount(double trueAmount) {
        this.trueAmount = trueAmount;
    }

    /**
     * Měna účtu.
     *
     * @return měna účtu
     */
    @Column(updatable = false)
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Nastavení měny účtu. Měnu lze nastavit pouze při založení účtu.
     *
     * @param currency měna účtu
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Příznak, zda je dispozice s účtem uživateli zablokována.
     *
     * @return přííznak blokace
     */
    @Column
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Nastavení blokace účtu.
     * Operace je idempotentní.
     *
     * @param blocked příznak blokace
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * Vazba na majjitele účtu pomocí Customer ID
     *
     * @return Objekt majitele účtu
     */
    @ManyToOne
    @JoinColumn(name = "customer", updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Nelze měnit dispozici s účtem po jeho založení.
     *
     * @param customer majitel účtu
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Vrací seznam všech pohybů svázaných s účtem.
     *
     * @return seznam všech pohybů na účtu
     */
    @OneToMany
    @JoinColumn(name = "id")
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Setter pro kolekci pohybů na účtu
     *
     * @param moves sznam pohyb na účtu
     */
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
