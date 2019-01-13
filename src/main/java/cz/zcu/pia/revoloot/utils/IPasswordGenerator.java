package cz.zcu.pia.revoloot.utils;

/**
 * Služba pro generování uživatelských jmen dle bankovích pravidel
 */
public interface IPasswordGenerator {

    /**
     * Meotda vygeneruje náhodný login splňující pravidla banky
     * Nevyučuje vygenerování již existujícího loginu
     * @return login
     */
    String generateLogin();

    /**
     * Vygeneruje náhodné heslo dle pravidel generování
     * @return nové heslo plaintext
     */
    String generatePassword();
}
