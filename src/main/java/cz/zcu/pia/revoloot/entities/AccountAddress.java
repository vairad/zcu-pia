package cz.zcu.pia.revoloot.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Entitní třída pro uložení čísla účtu.
 *
 * @author Radek Vais
 */
@Embeddable
public class AccountAddress {

    /**
     * předčíslí účtu
     */
    private long prepend;
    /**
     * číslo účtu
     */
    private long number;
    /**
     * kód banky
     */
    private int bankCode;

    /**
     * Vrací hodnotu předčíslí účtu
     *
     * @return předšíslí účtu 0, pokud není nastavené
     */
    @Column(updatable = false)
    public long getPrepend() {
        return prepend;
    }

    /**
     * Setter pro nastavení předčíslí účtu.
     *
     * @param prepend hodnota předčíslí - 0 pokud je rušeno
     */
    public void setPrepend(long prepend) {
        this.prepend = prepend;
    }


    /**
     * Vrací hodnotu čísla účtu
     *
     * @return číslo účtu 0 pokud není nastavené
     */
    @Column(updatable = false)
    public long getNumber() {
        return number;
    }

    /**
     * Setter pro nastavení čísla účtu
     *
     * @param number hodnota čísla účtu
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Vrací hodnoty kódu banky
     *
     * @return kód banky, 0 pokud není nastavené
     */
    @Column(updatable = false)
    public int getBankCode() {
        return bankCode;
    }

    /**
     * Setter pro nastavení kódu banky
     *
     * @param bankCode hodnota kódu banky, 0 pokud je rušeno
     */
    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Vrací sloučený zápis čísla účtu ve formátu:
     * prepend-number/bankCode
     * např 12-58632/3666
     *
     * @return sloučený zápis čísla účtu
     */
    @Override
    public String toString() {
        return prepend == 0 ? number + "/" + bankCode : prepend + "-" + number + "/" + bankCode;
    }

    /**
     * Dva účty jsou shodné právě tehdy, když mají stejnou banku, předčíslí i číslo účtu.
     *
     * @param o objekt k porovnání
     * @return true / false pokud jde o stejný účet
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountAddress)) return false;
        AccountAddress that = (AccountAddress) o;
        return bankCode == that.bankCode &&
                prepend == that.prepend &&
                number == that.number;
    }

    /**
     * Do hashovacího algoritmu vstupují všechny atributy objektu.
     * prepend
     * number
     * bankCode
     *
     * @return hash objektu
     */
    @Override
    public int hashCode() {
        return Objects.hash(prepend, number, bankCode);
    }
}
