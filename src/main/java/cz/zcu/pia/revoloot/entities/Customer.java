package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = TableConfig.TABLE_CUSTOMERS)
@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_customer"))
public class Customer extends User implements IValidable {

    private List<Account> accountList;
    private ContactInfo contactInfo;
    private Date birthDate;
    private Long personID;
    private String cardID;

    @Column(nullable = false)
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Column(nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(nullable = false)
    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
    }

    /**
     * Metoda nastaví rodné číslo ve formátu YYMMDD/xxxx nebo YYMMDDxxxx
     *
     * @param parameter rodné číslo CZ
     */
    public void setPersonIDSmart(String parameter) {
        String id;
        if (parameter.contains("/")) {
            String[] parts = parameter.split("/", 2);
            id = parts[0] + parts[1];
        } else {
            id = parameter;
        }
        try {
            personID = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            personID = null;
        }
    }


    /**
     * Metoda vrací rodné číslo v CZ formátu
     *
     * @return YYMMDD/XXXX pokud je z ČR
     */
    @Transient
    public String getPrintPersonID() {
        if (contactInfo != null && contactInfo.getAddress() != null) {
            State state = contactInfo.getAddress().getState();
            if (state != null && state.isCZ()) {
                String idS = Long.toString(personID);
                return idS.substring(0, 6) + "/" + idS.substring(6, 10);
            }
        }
        return Long.toString(personID);
    }

    @Column(nullable = false)
    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(contactInfo, customer.contactInfo) &&
                Objects.equals(birthDate, customer.birthDate) &&
                Objects.equals(personID, customer.personID) &&
                Objects.equals(cardID, customer.cardID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contactInfo, birthDate, personID, cardID);
    }


    //region user details

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @OneToMany(mappedBy = "customer")
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * Vrací zákaznícké číslo pro sjenání produktu
     *
     * @return zákaznické číslo
     */
    @Transient
    public long getRBI() {
        return getId();
    }

    //endregion

    //region IValidable

    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>(super.validate(validator));

        if (contactInfo != null) {
            errors.addAll(contactInfo.validate(validator));
        } else {
            errors.addAll(new ContactInfo().errorFields());
        }

        if (validator.isEmptyField(birthDate)) {
            errors.add(FormConfig.BIRTH_DATE);
        }

        if (birthDate == null || personID == null ||
                !validator.checkBirthAgainstPersonID(birthDate, getGender(), personID)) {
            errors.add(FormConfig.PERSON_ID);
        }

        if (validator.isEmptyField(cardID)) {
            errors.add(FormConfig.CARD_ID);
        }

        return errors;
    }

    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>(super.errorFields());
        errors.addAll(new ContactInfo().errorFields());
        errors.add(FormConfig.CARD_ID);
        errors.add(FormConfig.PERSON_ID);
        errors.add(FormConfig.BIRTH_DATE);
        return errors;
    }


    //endregion IValidable
}
