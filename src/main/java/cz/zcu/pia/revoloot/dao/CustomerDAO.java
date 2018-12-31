package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;


@Repository
public class CustomerDAO extends GenericDAO<Customer> implements ICustomerDAO {

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
}
