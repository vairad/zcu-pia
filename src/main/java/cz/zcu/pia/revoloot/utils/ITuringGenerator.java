package cz.zcu.pia.revoloot.utils;

/**
 * Služba pro zpracování a vyhodnocování Turingovy otázky
 *
 * @author Radek VAIS
 */
public interface ITuringGenerator {

    /**
     * Metoda ověří, zda odpověď na otázku s uuid je opravdu answer.
     * Po pokusu odpovědět je otázka vyřazena (dle uuid).
     *
     * @param answer hodnota odpovědi (null able)
     * @param uuid jednoznačné označneí otázky (null able)
     * @return true, pokud je odpověď správná, jinak false
     */
    boolean validateAnswer(String answer, String uuid);

    /**
     * Metoda vytvoří otázku v objektu a vrátí její UUID identifikátor
     * @return uuid otázky
     */
    String generateQuestion();

    /**
     * Meotda vrací text otázky dle zvoleného uuid
     * @return text oztázky
     */
    String getQuestionRepresentation(String uuid);
}
