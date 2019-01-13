package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;

import java.util.List;

public interface ICustomerManager {

    void register(boolean save, Customer newCustomer) throws CustomerValidationException;

    List<Customer> getAll(Pages pages);

    List<Account> getAllAccounts(long customerID);

    List<Account> getAllNonDebetAccounts(long customerID);

    Customer getByUsername(String username);

    Customer getCustomerByRBI(Long accNo);

    void updateCustomerInfo(boolean save, ContactInfo changes, Customer user) throws CustomerValidationException;

    void updateCustomer(boolean save, Customer changes, Customer user, User banker) throws CustomerValidationException;

    void removeCustomer(Customer customer, User banker);
}
