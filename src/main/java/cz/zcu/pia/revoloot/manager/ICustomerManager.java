package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;

import java.util.List;

public interface ICustomerManager {

    void register(Customer newCustomer) throws CustomerValidationException;

    List<Customer> getAll();

    List<Account> getAllAccounts(long customerID);

    List<Account> getAllNonDebetAccounts(long customerID);

    Customer getByUsername(String username);

    Customer getCustomerByRBI(Long accNo);
}
