package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerManager {
    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public void register(Customer newCustomer) throws CustomerValidationException {

    }
}
