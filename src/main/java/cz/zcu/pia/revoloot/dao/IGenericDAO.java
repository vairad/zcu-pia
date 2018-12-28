package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.BaseEntity;

/**
 * Rozhraní deklaruje základní vlastnosti pro manipulátor objektů (DAO)
 */
public interface IGenericDAO<T extends BaseEntity> {

    T save(T value);

    T findOne(Long id);

    void remove(T toRemove);
}
