package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IUserDAO;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.utils.IEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Třída spravující objekty typu User v DB
 *
 * @author Radek VAIS
 */
@Repository
public class UserDAO extends GenericDAO<User> implements IUserDAO {

    private Logger logger = LoggerFactory.getLogger(UserDAO.class.getName());

    /**
     * Objekt pro šifrování hesla
     */
    private final IEncoder encoder;


    /**
     * Konstruktor volá rodiče s parametrem entity User
     *
     * @param encoder objekt pro šifrování hesla a ověřování
     * @see GenericDAO
     * @see User
     * @see IEncoder
     */
    @Autowired
    public UserDAO(IEncoder encoder) {
        super(User.class);
        this.encoder = encoder;
    }

    /**
     * Testing constructor
     * - umožňuje podvržení EntityManageru
     *
     * @param em entity manager pro ukládání dat
     */
    public UserDAO(EntityManager em, IEncoder encoder) {
        super(em, User.class);
        this.encoder = encoder;
        logger.info("User DAO created rich constructor");
    }

    /**
     * Metoda najde uživatele dle uživatelského jména
     *
     * @return Nalezený objekt uživatele / null pokud neexistuje
     */
    @Override
    public User findByUsername(String login) {
        logger.info("Find user by username: " + login);

        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.login = :uname", User.class);
        q.setParameter("uname", login);
        try {
            User user = q.getSingleResult();
            logger.info("User found" + user);
            return user;
        } catch (NoResultException e) {
            logger.debug("No user found for login: " + login);
            //no result found
            return null;
        }
    }

    /**
     * Metoda ověří zda heslo odpovídá uživateli dle přihlašovacího jména.
     *
     * @param login    reprezentace uživatelského jména
     * @param password heslo
     * @return true pokud heslo odpovídá uživateli jinak false
     */
    @Override
    public boolean authenticate(String login, String password) {
        User user = findByUsername(login);

        if (user == null) {
            return false;
        }

        return encoder.validate(password, user.getPassword());
    }

    /**
     * Metoda vrací údaje o uživateli pro Security službu
     *
     * @param username uřivatelské jméno
     * @return nalezený živatel
     * @throws UsernameNotFoundException v případě neexistujícího uživatele
     * @see UserDetailsService
     * @see UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    /**
     * Metoda ověří, zda login již existuje v DB či nikoliv
     *
     * @param login login k ověření
     * @return true pokud login v parametru existuje false jindy
     */
    @Override
    public boolean existLogin(String login) {
        return findByUsername(login) != null;
    }
}
