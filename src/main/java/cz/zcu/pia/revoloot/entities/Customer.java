package cz.zcu.pia.revoloot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = TableConfig.TABLE_CUSTOMERS)
@PrimaryKeyJoinColumn(name="user")
public class Customer extends User {

    private List<Account> accountList;
    private ContactInfo contactInfo;

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
