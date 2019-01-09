package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entitní třída pro uložení čísla účtu.
 *
 * @author Radek Vais
 */
@Embeddable
public class AccountAddress implements IValidable {

    /**
     * předčíslí účtu
     */
    private Long prepend;
    /**
     * číslo účtu
     */
    private Long number;
    /**
     * kód banky
     */
    private Integer bankCode;

    /**
     * Vrací hodnotu předčíslí účtu
     *
     * @return předšíslí účtu 0, pokud není nastavené
     */
    @Column(updatable = false)
    public Long getPrepend() {
        return prepend;
    }

    /**
     * Setter pro nastavení předčíslí účtu.
     *
     * @param prepend hodnota předčíslí - 0 pokud je rušeno
     */
    public void setPrepend(Long prepend) {
        this.prepend = prepend;
    }


    /**
     * Vrací hodnotu čísla účtu
     *
     * @return číslo účtu 0 pokud není nastavené
     */
    @Column(updatable = false)
    public Long getNumber() {
        return number;
    }

    /**
     * Setter pro nastavení čísla účtu
     *
     * @param number hodnota čísla účtu
     */
    public void setNumber(Long number) {
        this.number = number;
    }

    /**
     * Vrací hodnoty kódu banky
     *
     * @return kód banky, 0 pokud není nastavené
     */
    @Column(updatable = false)
    public Integer getBankCode() {
        return bankCode;
    }

    /**
     * Setter pro nastavení kódu banky
     *
     * @param bankCode hodnota kódu banky, 0 pokud je rušeno
     */
    public void setBankCode(Integer bankCode) {
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
        if(prepend == null){
            return number + "/" + bankCode;
        }
        return prepend + "-" + number + "/" + bankCode;
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
        return bankCode.equals(that.bankCode) &&
                prepend.equals(that.prepend) &&
                number.equals(that.number);
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

    /**
     * Meotda validuje vyplnění polí objektu
     * @see cz.zcu.pia.revoloot.web.FormConfig
     * @param validator používá předaný vylidátor polí
     * @return množina chyb polí
     */
    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>();
        if (validator.isEmptyField(number)) {
            errors.add(FormConfig.ACC_NUM);
        }
        if (validator.isEmptyField(bankCode)) {
            errors.add(FormConfig.BANK_CODE);
        }

        return errors;
    }

    /**
     * Maximální množina chyb na polích
     * @see cz.zcu.pia.revoloot.web.FormConfig
     * @param
     * @return maximální množina chyb polí
     */
    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>();
        errors.add(FormConfig.ACC_NUM);
        errors.add(FormConfig.BANK_CODE);

        return errors;
    }
}
