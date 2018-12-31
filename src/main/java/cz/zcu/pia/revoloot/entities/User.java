package cz.zcu.pia.revoloot.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = TableConfig.TABLE_USERS)
@Inheritance(strategy= InheritanceType.JOINED)
public class User extends BaseEntity implements UserDetails{

    //region Fields
    private String login;
    private String password;
    private Date created;

    private String name;
    private String surname;

    private boolean banker;
    // endregion

    /**
     * TODO comment
     */
    public User() {
        banker = false;
        created = new Date();
    }

    /**
     * TODO comment
     *
     */
    List<String> validate() {
       return null;
    }

    // region Mapping

    @Column(unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isBanker() {
        return banker;
    }

    public void setBanker(boolean banker) {
        this.banker = banker;
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
        if(isBanker()){
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_BANKER"));
        }
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
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

}
