package cz.zcu.pia.revoloot.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DaoTest {

    private static final String PERSISTENCE_UNIT = "cz.zcu.pia.revoloot.test";
    protected static EntityManager em;

    // region preprare tests
    @BeforeAll
    static void setUpConnection() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = factory.createEntityManager();
    }

    @BeforeEach
    void beginTxn() {
        em.getTransaction().begin();
    }

    @AfterEach
    void endTxn() {
        em.getTransaction().commit();
    }

    @AfterAll
    static void tearDownConnection() {
        em.close();
    }
}
