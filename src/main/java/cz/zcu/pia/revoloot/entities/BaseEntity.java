package cz.zcu.pia.revoloot.entities;


import javax.persistence.*;

/**
 * Rodič všech DB objektů.
 *
 * Garantuje existenci ID (PK)
 */
@MappedSuperclass
public class BaseEntity {

    private Long id;

    /**
     * Příznak uložení do db
     * @return true pokud entita nebyla uložena do db
     */
    @Transient
    public boolean isNew() {
        return id == null;
    }

    /**
     * Vrací sériové číslo objektu
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * Nastavuje seriové číslo
     * @param id seriové číslo
     */
    public void setId(Long id) {
        this.id = id;
    }
}
