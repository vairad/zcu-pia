package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.dao.IUserDAO;
import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.utils.IPasswordGenerator;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerManager implements ICustomerManager {

    private Logger logger = LoggerFactory.getLogger(CustomerManager.class.getName());

    private final IUserDAO userDAO;
    private final ICustomerDAO customerDAO;
    private final IValidator validator;

    private final IPasswordGenerator generator;

    @Autowired
    public CustomerManager(IValidator validator, ICustomerDAO customerDAO, IPasswordGenerator generator, IUserDAO userDAO) {
        this.validator = validator;
        this.customerDAO = customerDAO;
        this.generator = generator;
        this.userDAO = userDAO;
    }

    @Override
    public void register(Customer newCustomer) throws CustomerValidationException {
        newCustomer.setPassword(generator.generatePassword());

        String login = generator.generateLogin();
        while (userDAO.existLogin(login)){
            login = generator.generateLogin();
        }

        newCustomer.setLogin(login);

        Set<String> errors = newCustomer.validate(validator);
        if(!errors.isEmpty()){
            throw new CustomerValidationException(errors);
        }
        try {
            customerDAO.save(newCustomer);
        }catch (PersistenceException ex){
            logger.warn("Cant persist object", ex);
            errors.add("duplicate");
            throw new CustomerValidationException(errors);
        }
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.findAllCustomers();
    }

    @Override
    public List<Account> getAllAccounts(long customerID) {


    }


}
