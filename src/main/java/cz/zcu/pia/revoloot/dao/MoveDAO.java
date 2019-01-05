package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Repository
public class MoveDAO extends GenericDAO<Move> implements IMoveDAO {

    private Logger logger = LoggerFactory.getLogger(MoveDAO.class.getName());


    public MoveDAO() {
        super(Move.class);
    }

    /**
     * Testing constructor
     *
     * @param em
     */
    public MoveDAO(EntityManager em) {
        super(em, Move.class);
        logger.info("Account DAO created rich constructor");
    }

}
