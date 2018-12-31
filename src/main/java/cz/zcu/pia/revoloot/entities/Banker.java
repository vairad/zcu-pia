package cz.zcu.pia.revoloot.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = TableConfig.TABLE_BANKERS)
@PrimaryKeyJoinColumn(name="user", foreignKey = @ForeignKey(name = "fk_banker"))
public class Banker extends User {

    /** adresa působiště banovního poradce */
    private Address branch;
    /** název uložené fotky k nahrání */
    private String photo;
    /** kontaktní email pracovníka */
    private String email;

    /**
     * Adresu pobočky bankovního poradce
     */
    @Column
    @Embedded
    public Address getBranch() {
        return branch;
    }

    /**
     * Nastaví adresu pobočky bankovního poradce
     * @param branch nová adresa
     */
    public void setBranch(Address branch) {
        this.branch = branch;
    }


    /**
     * Název souboru s načítanou fotkou poradce.
     * Jde o název souboru uloženého do /img/banker/{name}.
     */
    @Column(unique = true)
    public String getPhoto() {
        return photo;
    }

    /**
     * Nastaví nový název obrázku.
     * @param photo název souboru uloženéého v /img/banker/
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }


    /**
     * Email bankovního poradce
     * @return email bankovního poradce
     */
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    /**
     * Nastaví nový email bankovního poradce
     * @param email nový emaily
     */
    public void setEmail(String email) {
        this.email = email;
    }


    //region user details

    /**
     * Metoda garantující roli bankovního poradce.
     * @return Kolekce rolí
     */
    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_BANKER"));
    }

    //endregion
}
