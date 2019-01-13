package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.dao.IUserDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.IMailSender;
import cz.zcu.pia.revoloot.utils.IPasswordGenerator;
import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
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
    private final IAccountDAO accountDAO;
    private final IValidator validator;
    private final IMailSender mailSender;
    private final IEncoder encoder;

    private final IPasswordGenerator generator;

    @Autowired
    public CustomerManager(IValidator validator, ICustomerDAO customerDAO, IPasswordGenerator generator, IUserDAO userDAO, IAccountDAO accountDAO, IMailSender mailSender, IEncoder encoder) {
        this.validator = validator;
        this.customerDAO = customerDAO;
        this.generator = generator;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.mailSender = mailSender;
        this.encoder = encoder;
    }

    @Override
    public void register(boolean save, Customer newCustomer) throws CustomerValidationException {
        String passwordPlain = generator.generatePassword();
        newCustomer.setPassword(encoder.encode(passwordPlain));

        String login = generator.generateLogin();
        while (userDAO.existLogin(login)) {
            login = generator.generateLogin();
        }

        newCustomer.setLogin(login);

        Set<String> errors = newCustomer.validate(validator);
        if (!save) {
            errors.add(FormConfig.TURING);
        }
        if (!errors.isEmpty()) {
            throw new CustomerValidationException(errors);
        }
        try {
            customerDAO.save(newCustomer);
            if (mailSender != null) {
                mailSender.sendCreationMessage(newCustomer.getContactInfo().getEmail(), login, passwordPlain);
            }
        } catch (PersistenceException ex) {
            logger.warn("Cant persist object", ex);
            errors.add("duplicate");
            throw new CustomerValidationException(errors);
        }
    }

    @Override
    public List<Customer> getAll(Pages pages) {
        return customerDAO.findAllCustomers(pages);
    }

    @Override
    public List<Account> getAllAccounts(long customerID) {
        List<Account> foundedAccounts = accountDAO.findByUserId(customerID);
        return foundedAccounts;
    }

    @Override
    public Customer getByUsername(String username) {
        return customerDAO.findByUsername(username);
    }

    /**
     * Metoda vyhledá uživatele dle zákaznického ID (RBI - revoloot banking index)
     *
     * @param rbi reprezentace zákkaznického ID
     * @return Objekt User / null v případě chyby, nebo neexistujícího záznamu.
     */
    @Override
    public Customer getCustomerByRBI(Long rbi) {
        Customer customer = customerDAO.findByRBI(rbi);
        return customer;
    }

    @Override
    public void updateCustomerInfo(boolean save, ContactInfo changes, Customer user) throws CustomerValidationException {
        if (changes != null) {
            Set<String> errors = changes.validate(validator);
            if (!save) {
                errors.add(FormConfig.TURING);
            }
            if (errors.isEmpty()) {
                ContactInfo oldContacts = user.getContactInfo();
                user.setContactInfo(changes);
                userDAO.save(user);
                if (mailSender != null) {
                    mailSender.sendUpdateMessage(user.getContactInfo().getEmail(), user);
                    if (!user.getContactInfo().getEmail().equals(oldContacts.getEmail())) {
                        mailSender.sendUpdateMessage(oldContacts.getEmail(), user);
                    }
                }
            } else {
                throw new CustomerValidationException(errors);
            }
        }
    }

    @Override
    public void updateCustomer(boolean save, Customer changes, Customer user, User banker) throws CustomerValidationException {
        if (changes != null) {
            changes.setPassword(user.getPassword());
            changes.setLogin(user.getLogin());
            changes.setId(user.getId());
            Set<String> errors = changes.validate(validator);
            if (!save) {
                errors.add(FormConfig.TURING);
            }
            if (errors.isEmpty()) {
                userDAO.save(changes);
                if (mailSender != null) {
                    mailSender.sendUpdateMessage(user.getContactInfo().getEmail(), banker);
                    if (!user.getContactInfo().getEmail().equals(changes.getContactInfo().getEmail())) {
                        mailSender.sendUpdateMessage(changes.getContactInfo().getEmail(), user);
                    }
                }
            } else {
                throw new CustomerValidationException(errors);
            }
        }
    }

    @Override
    public void removeCustomer(Customer customer, User banker) {
        Customer toRemove = customerDAO.findOne(customer.getId());
        if (mailSender != null) {
            mailSender.sendRemoveMessage(toRemove.getContactInfo().getEmail(), banker);
        }
        customerDAO.remove(toRemove);
    }
}
