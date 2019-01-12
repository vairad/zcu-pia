package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IGenericDAO;
import cz.zcu.pia.revoloot.entities.BaseEntity;
import cz.zcu.pia.revoloot.entities.Pages;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Třída spravující objekty typu <T> v DB
 * Obsahuje základní metody pro CRUD
 *
 * @param <T> typ ukládaného objektu
 * @author Radek VAIS
 */
public abstract class GenericDAO<T extends BaseEntity> implements IGenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> persistedType;

    GenericDAO(Class<T> persistedType) {
        this.persistedType = persistedType;
    }

    /**
     * Constructor pro testy
     * - podvržení entity mangeru
     *
     * @param persistedType type of the entity persisted by this DAO
     */
    GenericDAO(EntityManager em, Class<T> persistedType) {
        this(persistedType);
        this.em = em;
    }

    /**
     * Meotda uloží element
     *
     * @param value element k uložení
     * @return uložený element (uložení múže doplnit hodnoty - např ID)
     */
    @Override
    public T save(T value) {
        if (value.isNew()) {
            em.persist(value);
            return value;
        } else {
            return em.merge(value);
        }
    }

    /**
     * Metoda najde element dle primárního klíče ID
     *
     * @param id primární klíč
     * @return nalezený objekt / null
     */
    @Override
    public T findOne(Long id) {
        return em.find(persistedType, id);
    }

    /**
     * Metoda odstraní objekt z databáze
     *
     * @param toRemove objekt k odstranění
     */
    @Override
    public void remove(T toRemove) {
        if (!toRemove.isNew()) {
            em.remove(toRemove);
        }
    }


    void resolvePageing(Pages pages, TypedQuery<Long> countQuery) {
        Long countResults;
        try {
            countResults = countQuery.getSingleResult();
        } catch (NoResultException e) {
            countResults = 0L;
        }

        pages.setPagesCount(countResults / pages.getPageSize());
    }
}
