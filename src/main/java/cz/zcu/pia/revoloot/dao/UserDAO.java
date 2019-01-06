package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.utils.IEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;



@Repository
public class UserDAO extends GenericDAO<User> implements IUserDAO {

    private Logger logger = LoggerFactory.getLogger(UserDAO.class.getName());

    private final IEncoder encoder;

    @Autowired
    public UserDAO(IEncoder encoder) {
        super(User.class);
        this.encoder = encoder;
    }

    /**
     * Testing constructor
     *
     * @param em
     * @param encoder
     */
    public UserDAO(EntityManager em, IEncoder encoder) {
        super(em, User.class);
        this.encoder = encoder;
        logger.info("User DAO created rich constructor");
    }

    /**
     * TODO comment
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
     * TODO comment
     */
    @Override
    public boolean authenticate(String login, String password) {
        User user = findByUsername(login);

        if (user == null) {
            return false;
        }

        return encoder.validate(password, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public boolean existLogin(String login) {
        return findByUsername(login) != null;
    }
}
