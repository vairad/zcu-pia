package cz.zcu.pia.revoloot.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * TODO comment
 */
@Component
public class PasswordHashEncoder implements IEncoder, PasswordEncoder {

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
            e.printStackTrace();
            //TODO log!
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            //TODO log!
            return null;
        }
    }

    @Override
    public boolean validate(String text, String hash) {
        try {
            return PasswordHash.validatePassword(text, hash);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //TODO log!
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            //TODO log!
            return false;
        }
    }
}
