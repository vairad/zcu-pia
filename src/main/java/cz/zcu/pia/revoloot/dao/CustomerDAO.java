package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;

import javax.persistence.EntityManager;

public class CustomerDAO extends GenericDAO<Customer> implements ICustomerDAO {

    /**
     * TODO comment
     *
     * @param em
     * @param persistedType type of the entity persisted by this DAO
     */
    public CustomerDAO(EntityManager em) {
        super(em, Customer.class);
    }

    @Override
    public Customer findByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public Customer findByCustomerId(String customerID) {
        return null;
    }
}
