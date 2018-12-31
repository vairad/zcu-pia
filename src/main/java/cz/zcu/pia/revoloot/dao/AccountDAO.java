package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class AccountDAO extends GenericDAO<Account> implements IAccountDAO {

    private Logger logger = LoggerFactory.getLogger(AccountDAO.class.getName());

    public AccountDAO() {
        super(Account.class);
    }

    /**
     * Testing constructor
     *
     * @param em
     */
    public AccountDAO(EntityManager em) {
        super(em, Account.class);
        logger.info("Account DAO created rich constructor");
    }

    @Override
    public Account findByAccountNumber(long accNo) {
        logger.info("Find user by username: " + accNo);

        TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a WHERE a.accountInfo.number = :accNo", Account.class);
        q.setParameter("accNo", accNo);
        try {
            Account account = q.getSingleResult();
            logger.info("Account found" + account);
            return account;
        } catch (NoResultException e) {
            logger.debug("No account found for account number: " + accNo);
            //no result found
            return null;
        }
    }
}
