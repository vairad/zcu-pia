package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IExchangeDAO;
import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class ExchangeDAO extends GenericDAO<ExchangeRate> implements IExchangeDAO {

    private Logger logger = LoggerFactory.getLogger(ExchangeDAO.class.getName());

    /**
     * Konstruktor volá rodiče s parametrem entity Account
     *
     * @see GenericDAO
     * @see ExchangeRate
     */
    ExchangeDAO() {
        super(ExchangeRate.class);
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    public ExchangeDAO(EntityManager em) {
        super(em, ExchangeRate.class);
    }


    @Override
    public double getExchchangeRate(Currency from, Currency to) {
        logger.info("Find exchange rate: " + from + " -> " + to);

        TypedQuery<ExchangeRate> q = em.createQuery("SELECT ex FROM ExchangeRate ex " +
                "WHERE ex.fromCur = :fromCur AND ex.toCur = :toCur ORDER BY ex.validFrom DESC ", ExchangeRate.class);
        q.setParameter("fromCur", from);
        q.setParameter("toCur", to);
        q.setMaxResults(1);
        try {
            ExchangeRate exchangeRate = q.getSingleResult();
            logger.info("Account found" + exchangeRate);
            return exchangeRate.getRate();
        } catch (NoResultException e) {
            logger.debug("No exchange rate found: " + from + " -> " + to);
            throw new NullPointerException("No echange rate");
        }
    }
}

