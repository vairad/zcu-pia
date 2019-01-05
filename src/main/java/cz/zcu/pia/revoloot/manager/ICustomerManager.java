package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;

public interface ICustomerManager {

    /**
     * @param newCustomer
     * @throws CustomerValidationException
     */
    void register(Customer newCustomer) throws CustomerValidationException;

}
