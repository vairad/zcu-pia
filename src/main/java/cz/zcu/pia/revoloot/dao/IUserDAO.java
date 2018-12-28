package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.User;

public interface IUserDAO extends IGenericDAO<User> {

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
}
