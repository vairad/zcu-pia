package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.entities.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

}
