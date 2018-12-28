package cz.zcu.pia.revoloot.utils;

/**
 * TODO comment
 */
public interface IEncoder {

    /**
     * Returns hash of the passed text.
     * @param text input plaintext form
     * @return output = crypted text
     */
    String encode(String text);

    /**
     * Validates that the text is the plaintext form associated with the hash.
     * @param text plaintext form
     * @param hash hash for comparison
     * @return true of correct
     */
    boolean validate(String text, String hash);
}
