package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Třída spravující objekty typu Account v DB
 *
 * @author Radek VAIS
 */
@Repository
public class AccountDAO extends GenericDAO<Account> implements IAccountDAO {

    private Logger logger = LoggerFactory.getLogger(AccountDAO.class.getName());

    /**
     * Konstruktor volá rodiče s parametrem entity Account
     *
     * @see GenericDAO
     * @see Account
     */
    public AccountDAO() {
        super(Account.class);
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    public AccountDAO(EntityManager em) {
        super(em, Account.class);
        logger.info("Account DAO created rich constructor");
    }

    /**
     * Metoda vyhledá účet podle čísla účtu
     *
     * @param accNo číslo místního účtu (accountAddress.number)
     * @return objekt čísla účtu / null pokud neexistuje
     */
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

    /**
     * Nalezne seznam účtů dle ID uživatele
     *
     * @param customerID userID daného zákazníka
     * @return seznam účtů / null, pokud nebyl zádný nalezen
     */
    @Override
    public List<Account> findByUserId(long customerID) {
        logger.info("Find accounts for user: " + customerID);

        TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a WHERE a.customer.id = :customerID", Account.class);
        q.setParameter("customerID", customerID);
        try {
            List<Account> account = q.getResultList();
            logger.info("Account some accounts found.");
            return account;
        } catch (NoResultException e) {
            logger.debug("No accounts found for user id: " + customerID);
            //no result found
            return null;
        }
    }

    @Override
    public Account checkAccount(Long userId, Long accNo) {
        logger.info("Find accounts for user: " + userId + " accNo " + accNo);

        TypedQuery<Account> q = em.createQuery("SELECT a FROM Account a " +
                "WHERE a.customer.id = :customerID AND a.accountInfo.number = :accNo", Account.class);
        q.setParameter("customerID", userId);
        q.setParameter("accNo", accNo);

        try {
            Account account = q.getSingleResult();
            logger.info("Account some accounts found.");
            return account;
        } catch (NoResultException e) {
            logger.debug("User" + userId + " not owe accNo: " + accNo);
            //no result found
            return null;
        }
    }

    @Override
    public Long findMaxAccountNumber() {
        logger.info("Find max account number");

        TypedQuery<Long> q = em.createQuery("SELECT max(a.accountInfo.number)  FROM Account a", Long.class);

        try {
            Long accountNumber = q.getSingleResult();
            logger.info("Max account number was found: " + accountNumber);
            return accountNumber;
        } catch (NoResultException e) {
            logger.debug("Max account number was not found");
            //no result found
            return null;
        }
    }
}
