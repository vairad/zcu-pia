package cz.zcu.pia.revoloot.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ManagerBaseTest {

    private static final String PERSISTENCE_UNIT = "cz.zcu.pia.revoloot.test";
    protected EntityManager em;

    void setUpConnection() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }


    void endConnection() {
        em.getTransaction().commit();
        em.close();
    }

}
