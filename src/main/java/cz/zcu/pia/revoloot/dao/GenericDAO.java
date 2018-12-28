package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * todo comment
 * @param <T>
 */
public class GenericDAO<T extends BaseEntity> implements IGenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;
    private Class<T> persistedType;

    /**
     * TODO comment
     * @param persistedType type of the entity persisted by this DAO
     */
    public GenericDAO(EntityManager em, Class<T> persistedType) {
        this.persistedType = persistedType;
        this.em = em;
    }

    @Override
    public T save(T value) {
        if(value.isNew()) {
            em.persist(value);
            return value;
        } else {
            return em.merge(value);
        }
    }

    @Override
    public T findOne(Long id) {
        return em.find(persistedType, id);
    }

    @Override
    public void remove(T toRemove) {
        if(!toRemove.isNew()) {
            em.remove(toRemove);
        }
    }
}
