package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IBankerDAO;
import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Banker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Třída spravující objekty typu Banker v DB
 *
 * @author Radek VAIS
 */
@Repository
public class BankerDAO extends GenericDAO<Banker> implements IBankerDAO {

    private Logger logger = LoggerFactory.getLogger(BankerDAO.class.getName());

    /**
     * Konstruktor volá rodiče s parametrem entity Banker
     * @see GenericDAO
     * @see Banker
     */
    BankerDAO() {
        super(Banker.class);
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    BankerDAO(EntityManager em) {
        super(em, Banker.class);
    }


    /**
     * Metoda načte všechny veřejné bankéře.
     * {@link #loadAllBankers(boolean)}
     * @return seznam všech veřejných bankéřů (prázný pokud nenalezeno)
     */
    @Override
    public List<Banker> loadAllPublicBankers() {
        return loadAllBankers(false);
    }

    /**
     * Metoda načte všechny privátní bankéře
     * {@link #loadAllBankers(boolean)}
     * @return seznam všech privátních bankéřů (prázný pokud nenalezeno)
     */
    @Override
    public List<Banker> loadAllSecretBankers() {
        return loadAllBankers(true);
    }

    /**
     * Metoda načte všechny bankéře dle flajky veřejnosti
     * @param secFlag true - pro privátní / false - pro vřejné
     * @return seznam všech bankéřů (prázný pokud nenalezeno)
     */
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
            return new LinkedList<>();
        }
    }
}
