package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CustomerDAO extends GenericDAO<Customer> implements ICustomerDAO {

    private Logger logger = LoggerFactory.getLogger(CustomerDAO.class.getName());

    /**
     * TODO comment
     */
    public CustomerDAO(){
        super(Customer.class);
    }

    /**
     * TODO comment
     */
    public CustomerDAO(EntityManager em) {
        super(em, Customer.class);
    }

    /**
     * TODO comment
     * @param accountNumber reprezentace čísla účtu.
     * @return
     */
    @Override
    public Customer findByAccountNumber(String accountNumber) {
        return null;
    }

    /**
     * TODO comment
     * @param customerID reprezentace zákkaznického ID
     * @return
     */
    @Override
    public Customer findByCustomerId(String customerID) {
        return null;
    }

    /**
     * TODO comment
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
}
