package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * todo comment
 *
 * @param <T>
 */
public abstract class GenericDAO<T extends BaseEntity> implements IGenericDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> persistedType;


    GenericDAO(Class<T> persistedType) {
        this.persistedType = persistedType;
    }

    /**
     * Constructor for testing
     *
     * @param persistedType type of the entity persisted by this DAO
     */
    GenericDAO(EntityManager em, Class<T> persistedType) {
        this(persistedType);
        this.em = em;
    }

    @Override
    public T save(T value) {
        if (value.isNew()) {
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
        if (!toRemove.isNew()) {
            em.remove(toRemove);
        }
    }
}
