package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Banker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BankerDAO extends GenericDAO<Banker> implements IBankerDAO {

    private Logger logger = LoggerFactory.getLogger(BankerDAO.class.getName());

    BankerDAO() {
        super(Banker.class);
    }

    /**
     * Constructor for testing usage
     *
     * @param em entity manager
     */
    BankerDAO(EntityManager em) {
        super(em, Banker.class);
    }


    @Override
    public List<Banker> loadAllPublicBankers() {
        return loadAllBankers(false);
    }

    @Override
    public List<Banker> loadAllSecretBankers() {
        return loadAllBankers(true);
    }

    private List<Banker> loadAllBankers(boolean secFlag) {
        logger.info("Load all bankers with secFlag:" + secFlag);

        TypedQuery<Banker> q = em.createQuery("SELECT b FROM Banker b WHERE b.secret = :secFlag", Banker.class);
        q.setParameter("secFlag", secFlag);
        try {
            List<Banker> bankers = q.getResultList();
            logger.info("Some Bankers found");
            return bankers;
        } catch (NoResultException e) {
            logger.debug("No bankers found for secFlag: " + secFlag);
            //no result found
            return null;
        }
    }
}
