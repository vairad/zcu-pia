package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDAO extends IGenericDAO<User>, UserDetailsService {

    /**
     * Metoda vyhledá uřivatele dle přihlašovacího jména.
     *
     * @param login reprezentace přihlašovacího jména.
     * @return Objekt User / null v případě chyby, nebo neexistujícího záznamu.
     */
    User findByUsername(String login);


    /**
     * Metoda ověří zda heslo odpovídá uživateli dle přihlašovacího jména.
     *
     * @param login    reprezentace uživatelského jména
     * @param password heslo
     * @return true pokud heslo odpovídá uživateli jinak false
     */
    boolean authenticate(String login, String password);

    /**
     * TODO comment
     * authorization mezhod
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * TODO
     * @param login
     * @return
     */
    boolean existLogin(String login);
}
