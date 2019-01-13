package cz.zcu.pia.revoloot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Třída pro cryptování hesla obaluje {@link PasswordHash}
 * @see PasswordHash
 *
 * @author Radek Vais
 */
@Component
public class PasswordHashEncoder implements IEncoder, PasswordEncoder {

    private Logger logger = LoggerFactory.getLogger(CzechFormatter.class.getName());

    @Override
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return validate(rawPassword.toString(), encodedPassword);
    }

    @Override
    public String encode(String text) {
        try {
            return PasswordHash.createHash(text);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Neznámý šifrovací algotritmus", e);
            return null;
        } catch (InvalidKeySpecException e) {
            logger.error("Neznámý popis klíče", e);
            return null;
        }
    }

    @Override
    public boolean validate(String text, String hash) {
        try {
            return PasswordHash.validatePassword(text, hash);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Neznámý šifrovací algotritmus", e);
            return false;
        } catch (InvalidKeySpecException e) {
            logger.error("Neznámý popis klíče", e);
            return false;
        }
    }
}
