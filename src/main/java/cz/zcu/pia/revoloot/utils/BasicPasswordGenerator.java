package cz.zcu.pia.revoloot.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BasicPasswordGenerator implements IPasswordGenerator {

    private Random rnd = new Random();
    private final String loginchars = "QWERTZUIOPASDFGHJKYXCVBNM";

    String prefix = "A";

    @Override
    public String generateLogin() {
        String number = String.format("%03d", rnd.nextInt(999));
        char char1 = loginchars.charAt(rnd.nextInt(loginchars.length() - 1));
        char char2 = loginchars.charAt(rnd.nextInt(loginchars.length() - 1));
        return prefix +  number + char1 + char2;
    }

    @Override
    public String generatePassword() {
        return "pass";
    }
}
