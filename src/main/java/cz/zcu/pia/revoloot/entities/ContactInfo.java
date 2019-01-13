package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Embeddable
public class ContactInfo implements IValidable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactInfo)) return false;
        ContactInfo that = (ContactInfo) o;
        return phone == that.phone &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, phone, email);
    }

    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>();

//       if(validator.isEmptyField(phone)){
//            errors.add(FormConfig.PHONE_NUMBER);
//        }

        if (address != null) {
            errors.addAll(address.validate(validator));
        }

        if (validator.isEmptyField(email)) {
            errors.add(FormConfig.EMAIL_1);
            errors.add(FormConfig.EMAIL_2);
        }

        return errors;
    }

    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>(new Address().errorFields());
//        errors.add(FormConfig.PHONE_NUMBER);
        errors.add(FormConfig.EMAIL_1);
        errors.add(FormConfig.EMAIL_2);

        return errors;
    }
}
