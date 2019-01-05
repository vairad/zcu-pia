package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entitní objekt Adrresy.
 *
 * @author Radek Vais
 */
@Embeddable
public class Address implements IValidable {

    /**
     * ulice
     */
    private String street;
    /**
     * číslo popisné
     */
    private String houseNo;
    /**
     * město
     */
    private String city;
    /**
     * poštovní směrovací číslo
     */
    private Integer postalCode;

    /**
     * Stát
     */
    private State state;

    /**
     * Validační metoda ověří, zda jsou atributy vyplněné.
     * Nejsou akceptovány pouze prázdné hodnoty.
     *
     * @return pole šptaně vyplňených atributů
     * @see FormConfig
     */
    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>();
        //TODO refactor
        if (street == null || street.isEmpty()) {
            errors.add(FormConfig.STREET);
        }
        if (houseNo == null || houseNo.isEmpty()) {
            errors.add(FormConfig.HOUSE_NUMBER);
        }
        if (city == null || city.isEmpty()) {
            errors.add(FormConfig.CITY);
        }
        if (postalCode == null || postalCode == 0) {
            errors.add(FormConfig.POSTAL_CODE);
        }
        if (state == null) {
            errors.add(FormConfig.STATE);
        }
        return errors;
    }

    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>();
        errors.add(FormConfig.STREET);
        errors.add(FormConfig.HOUSE_NUMBER);
        errors.add(FormConfig.CITY);
        errors.add(FormConfig.POSTAL_CODE);
        errors.add(FormConfig.STATE);

        return errors;
    }

    /**
     * Vračí část adresy ulice
     *
     * @return ulice
     */
    @Column(nullable = false)
    public String getStreet() {
        return street;
    }

    /**
     * Nastaví hodnotu adresy ulice
     *
     * @param street ulice
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Vračí část adresy číslo popisné
     *
     * @return číslo popisné | null pokud není nastaveno
     */
    @Column(nullable = false)
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * Nastaví hodnotu adresy číslo popisné
     *
     * @param houseNo číslo popisné
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    /**
     * Vračí část adresy město
     *
     * @return město | null pokud není nastaveno
     */
    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    /**
     * Nastaví hodnotu adresy město
     *
     * @param city město
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Vračí část adresy poštovní směrovací číslo
     *
     * @return psč | null pokud není nastaveno
     */
    @Column(nullable = false)
    public Integer getPostalCode() {
        return postalCode;
    }

    /**
     * Nastaví hodnotu adresy poštovní směrovací číslo
     *
     * @param postalCode poštovní směrovací číslo
     */
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * přečte hodnotu státu
     *
     * @return stát adresy
     */
    @Column(nullable = false)
    public State getState() {
        return state;
    }

    /**
     * mastaví hodnotu státu
     *
     * @param state stát adresy
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Metoda vrací ustálený zápis adresy:
     * Ulice ČP, Město, PSČ
     *
     * @return Ulice ČP, Město, PSČ
     */
    @Override
    public String toString() {
        return street + " " + houseNo + ", " + city + ", " + postalCode;
    }

    /**
     * Dvě adresy jsou shodné právě tehdy, když všechn její prvky jsou shodné
     *
     * @param o objekt k porovnání
     * @return true pokud jsou adresy shodné
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(houseNo, address.houseNo) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode);
    }

    /**
     * Hash funkce k výpočtu využívá všechny atributy s výjímkou houseNo
     *
     * @return hash hodnota
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, city, postalCode);
    }
}
