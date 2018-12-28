package cz.zcu.pia.revoloot.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IEncoderTest {

    IEncoder encoder;

    @Test
    void validatePasswordHash() {
        encoder = new PasswordHashEncoder();
        String lockPassword = "ahoj";
        String lockHash = encoder.encode(lockPassword);

        String unlockPassword = "ahoj";

        assertTrue(encoder.validate(unlockPassword, lockHash), "Unlock mechanism is broken (" + encoder.getClass().getName() + ").");
    }

    @Test
    void validateEncryptionPasswordHash() {
        encoder = new PasswordHashEncoder();
        String lockPassword = "ahoj";
        String lockHash = encoder.encode(lockPassword);

        assertNotEquals(lockHash, lockPassword, "Implementation " + encoder.getClass().getName() + " does not provide encrypting password.");
    }
}