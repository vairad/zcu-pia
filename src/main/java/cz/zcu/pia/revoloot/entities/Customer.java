package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = TableConfig.TABLE_CUSTOMERS)
@PrimaryKeyJoinColumn(name="user", foreignKey = @ForeignKey(name = "fk_customer"))
public class Customer extends User implements IValidable {

    private List<Account> accountList;
    private ContactInfo contactInfo;
    private Date birthDate;
    private Long personID;
    private String cardID;

    @Column
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Column
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column
    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
    }

    /**
     * Metoda nastaví rodné číslo ve formátu YYMMDD/xxxx nebo YYMMDDxxxx
     * @param parameter rodné číslo CZ
     */
    public void setPersonIDSmart(String parameter) {
        String id;
        if(parameter.contains("/")){
            String[] parts = parameter.split("/",2);
            id =  parts[0] + parts[1];
        }else{
            id = parameter;
        }
        try {
            personID = Long.parseLong(id);
        }catch (NumberFormatException ex){
            personID = null;
        }
    }

    @Column
    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    //region user details

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @OneToMany
    @JoinColumn(name = "customer")
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }


    //endregion


    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>(super.validate(validator));

        if(contactInfo != null){
            errors.addAll(contactInfo.validate(validator));
        }

        if(validator.isEmptyField(birthDate)){
            errors.add(FormConfig.BIRTH_DATE);
        }

        if(validator.checkBirthAgainstPersonID(birthDate, getGender(), personID)){
            errors.add(FormConfig.PERSON_ID);
        }

        if(validator.isEmptyField(cardID)){
           errors.add(FormConfig.CARD_ID);
        }

        return errors;
    }
}
