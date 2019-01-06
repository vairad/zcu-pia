package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = TableConfig.TABLE_USERS)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity implements UserDetails, IValidable {

    //region Fields
    private String login;
    private String password;
    private Date created;

    private String name;
    private String surname;

    private Gender gender;
    // endregion

    /**
     * TODO comment
     */
    public User() {
        created = new Date();
    }


    // region Mapping

    @Column(unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(login != null ? !login.equals(user.login) : user.login != null);

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{"
                + "login='" + login + '\''
                + '}';
    }

    //region user details

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Transient
    @Override
    public String getUsername() {
        return getLogin();
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    //endregion user details

    /**
     * Metoda ověří, zda jsou všechny položky objektu správně vyplněné.
     *
     * @return množina chybných polí
     */
    @Override
    public Set<String> validate(IValidator validator) {
        Set<String> errors = new HashSet<>();
        if (validator.isEmptyField(name)) {
            errors.add(FormConfig.NAME);
        }
        if (validator.isEmptyField(surname)) {
            errors.add(FormConfig.SURNAME);
        }
        if (validator.isEmptyField(login)) {
            errors.add(FormConfig.LOGIN);
        }
        if (validator.isEmptyField(password)) {
            errors.add(FormConfig.PASSWORD);
        }
        if (gender == null) {
            errors.add(FormConfig.GENDER);
        }

        return errors;
    }

    @Override
    public Set<String> errorFields() {
        Set<String> errors = new HashSet<>();
        errors.add(FormConfig.NAME);
        errors.add(FormConfig.SURNAME);
        errors.add(FormConfig.LOGIN);
        errors.add(FormConfig.PASSWORD);
        errors.add(FormConfig.GENDER);

        return errors;
    }

}
