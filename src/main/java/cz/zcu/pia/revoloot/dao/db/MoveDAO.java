package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.Pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Třída spravující objekty typu Move v DB
 *
 * @author Radek VAIS
 */
@Repository
public class MoveDAO extends GenericDAO<Move> implements IMoveDAO {

    private Logger logger = LoggerFactory.getLogger(MoveDAO.class.getName());


    public MoveDAO() {
        super(Move.class);
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    public MoveDAO(EntityManager em) {
        super(em, Move.class);
        logger.info("Account DAO created rich constructor");
    }

    @Override
    public List<Move> getMovesToProcess(int limit) {
        logger.info("Load all moves to process");
        TypedQuery<Move> q = em.createQuery("SELECT m FROM Move m WHERE processed = :processed AND submissionDate < :date", Move.class);
        q.setParameter("processed", false);
        q.setParameter("date", new Date());
        if(limit > 0) {
            q.setMaxResults(limit);
        }
        try {
            List<Move> moves = q.getResultList();
            logger.info("Moves to process found");
            return moves;
        } catch (NoResultException e) {
            logger.debug("No moves found");
            //no result found
            return null;
        }
    }

    @Override
    public List<Move> findMovesForAccount(Account a, Pages pages) {
        TypedQuery<Long> countQuery = em.createQuery("SELECT count(m) FROM Move m WHERE m.owner = :account", Long.class);
        countQuery.setParameter("account", a);
        resolvePageing(pages, countQuery);

        TypedQuery<Move> q = em.createQuery("SELECT m FROM Move m WHERE m.owner = :account order by m.submissionDate DESC", Move.class);
        q.setParameter("account", a);
        resolvePageing(pages, countQuery);
        q.setMaxResults(pages.getPageSize());
        q.setFirstResult(pages.getOffset());

        try {
            List<Move> moves = q.getResultList();
            logger.info("Moves to show found");
            return moves;
        } catch (NoResultException e) {
            logger.debug("No moves found");
            //no result found
            return null;
        }
    }
}
