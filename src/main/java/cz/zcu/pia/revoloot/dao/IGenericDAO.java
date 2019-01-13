package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.BaseEntity;

/**
 * Rozhraní deklaruje základní vlastnosti pro manipulátor objektů (DAO)
 *
 * @author Radek VAIS
 */
public interface IGenericDAO<T extends BaseEntity> {


    /**
     * Uloží element
     * @param value element k uložení
     * @return uložený element
     */
    T save(T value);

    /**
     * Najde elemennt dle ID
     * @param id ID elementu
     * @return nalezený element / null
     */
    T findOne(Long id);

    /**
     * Odstraní element
     * @param toRemove element k odstranění
     */
    void remove(T toRemove);
}
