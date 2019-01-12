package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.entities.Banker;
import cz.zcu.pia.revoloot.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Třída spravující objekty typu Customer v DB
 *
 * @author Radek VAIS
 */
@Repository
public class CustomerDAO extends GenericDAO<Customer> implements ICustomerDAO {

    private Logger logger = LoggerFactory.getLogger(CustomerDAO.class.getName());

    /**
     * Konstruktor volá rodiče s parametrem entity Customer
     * @see GenericDAO
     * @see Customer
     */
    public CustomerDAO() {
        super(Customer.class);
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    public CustomerDAO(EntityManager em) {
        super(em, Customer.class);
    }

    /**
     * TODO comment
     *
     * @param accountNumber reprezentace čísla účtu.
     * @return
     */
    @Override
    public Customer findByAccountNumber(String accountNumber) {
        return null;
    }


    /**
     * TODO
     * @param username
     * @return
     */
    @Override
    public Customer findByUsername(String username) {
        logger.info("Load customer: " + username);
        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c WHERE c.login = :user", Customer.class);
        q.setParameter("user", username);
        try {
            Customer customer = q.getSingleResult();
            logger.info("Some Customer found: "+customer);
            return customer;
        } catch (NoResultException e) {
            logger.debug("No customers found");
            //no result found
            return null;
        }
    }

    /**
     * TODO comment
     *
     * @return
     */
    @Override
    public List<Customer> findAllCustomers() {
        logger.info("Load all customers:");

        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c WHERE 1 > 0", Customer.class);
        try {
            List<Customer> customerList = q.getResultList();
            logger.info("Some Customers found");
            return customerList;
        } catch (NoResultException e) {
            logger.debug("No customers found");
            //no result found
            return null;
        }
    }

    /**
     * Implementace vyhledání zákazníka pro entitu, která překrývá RBI a databázové ID
     * @param rbi zákaznické číslo
     * @return null/nalezený objekt
     */
    @Override
    public Customer findByRBI(Long rbi) {
        if(rbi == null){
            return null;
        }
        return findOne(rbi);
    }
}
