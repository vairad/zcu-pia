package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * DAO pro práci s uživateli
 *
 * @author Radek VAIS
 */
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
     * Metoda vrací údaje o uživateli pro Security službu
     *
     * @see UserDetailsService
     *
     * @param username uřivatelské jméno
     * @return nalezený živatel
     * @throws UsernameNotFoundException v případě neexistujícího uživatele
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Metoda ověří, zda login již existuje v DB či nikoliv
     * @param login login k ověření
     * @return true pokud login v parametru existuje false jindy
     */
    boolean existLogin(String login);
}
