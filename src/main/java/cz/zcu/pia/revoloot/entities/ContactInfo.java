package cz.zcu.pia.revoloot.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ContactInfo {

    private Address address;
    private int phone;
    private String email;

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
