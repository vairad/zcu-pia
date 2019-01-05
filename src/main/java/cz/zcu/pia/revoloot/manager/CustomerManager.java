package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerManager implements ICustomerManager {

    private final IValidator validator;

    @Autowired
    public CustomerManager(IValidator validator) {
        this.validator = validator;
    }

    @Override
    public void register(Customer newCustomer) {
        newCustomer.validate(validator);
    }
}
