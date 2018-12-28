package cz.zcu.pia.revoloot.utils;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static cz.zcu.pia.revoloot.utils.PasswordHash.createHash;
import static org.junit.jupiter.api.Assertions.*;


class PasswordHashTest {


    @Test
    void testHashEquality() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "p\r\nassw0Rd!";

        String hash = createHash(password);
        String secondHash = createHash(password);

        assertNotEquals(hash, secondHash, "Two hashes are equal");
    }

    @Test
    void testWrongPass() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "p\r\nassw0Rd!";

        String hash = createHash(password);
        String wrongPassword = "boo";

        assertFalse(PasswordHash.validatePassword(wrongPassword, hash), "Wrong pass is accepted.");
    }


    @Test
    void testGoodPass() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "p\r\nassw0Rd!";

        String hash = createHash(password);
        String goodPassword = "p\r\nassw0Rd!";

        assertTrue(PasswordHash.validatePassword(goodPassword, hash), "Wrong pass is accepted.");
    }

}