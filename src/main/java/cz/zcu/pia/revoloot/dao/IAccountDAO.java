package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * TODO COMMENT
 */
public interface IAccountDAO extends IGenericDAO<Account> {

    Account findByAccountNumber(long accNo);

    List<Account> findByUserId(long customerID);
}
