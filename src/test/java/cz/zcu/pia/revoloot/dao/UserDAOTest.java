package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest extends DaoTest {

    private static UserDAO userDAO;
    private static IEncoder encoder;


    static User prepareUser(String login, String password) {
        User user = new User();
        user.setCreated(new Date());
        user.setLogin(login);
        user.setPassword(encoder.encode(password));
        return user;
    }

    // region preprare tests
    @BeforeAll
    static void setUpDependencies() {
        encoder = new PasswordHashEncoder();
        userDAO = new UserDAO(em, encoder);

        for (int order = 0; order < 10; order++) {
            userDAO.save(prepareUser("login" + order, "pass" + order));
        }
    }

    //endregion

    //region Tests

    @Test
    void save() {
        User user = prepareUser("test", "pass");
        user = userDAO.save(user);

        assertNotEquals(user.getId(), 0L, "Id was not returned.");
    }

    @Test
    void findByUsername() {
        String login = "login2";
        User user = userDAO.findByUsername(login);

        assertNotNull(user, "User was not found.");
        assertEquals(user.getLogin(), login, "Returned wrong record");
    }

    @Test
    void findByUsernameFail() {
        String login = "NoLogin";
        User user = userDAO.findByUsername(login);

        assertNull(user, "User was found.");
    }

    @Test
    void findOne() {
        User user = userDAO.findOne(1L);

        assertEquals(user.getLogin(), "login0", "User login0 is not first record");
    }

    @Test
    void remove() {
        long toDelete = 10L;
        User user = userDAO.findOne(toDelete);
        userDAO.remove(user);

        User found = userDAO.findOne(toDelete);

        assertNull(found, "Object was not removed.");
    }

    @Test
    void authenticateOK() {
        String login = "pass1";
        String pass = "password";

        User u = prepareUser(login, pass);
        userDAO.save(u);

        boolean authenticated = userDAO.authenticate(login, pass);

        assertTrue(authenticated, "Auth fail.");
    }

    @Test
    void authenticateFail() {
        String login = "pass2";
        String pass = "password";

        User u = prepareUser(login, pass);
        userDAO.save(u);

        boolean authenticated = userDAO.authenticate(login, "mess");

        assertFalse(authenticated, "Auth not fail.");
    }
    //endregion tests
}