package cz.zcu.pia.revoloot.utils;

/**
 * Služba pro generování čísel účtů
 *
 * @author Radek Vais
 */
public interface IBankNumbers {

    /**
     * Vrací kód banky RevolutionLoot
     *
     * @return kód banky
     */
    int getBankCode();

    /**
     * Metoda využije předaného čísla a vypočte další chráněnné číslo ze sekvence
     *
     * @param maxAccNum poslední číslo sekvence
     * @return nové číslo účtu
     */
    long getNewAccNum(Long maxAccNum);

    /**
     * Metoda ověří, zda číslo je správné a je ze sekvence naší banky
     *
     * @param accNum číslo k ověření
     * @return true pokud ano jinak false
     */
    boolean validateNumber(Long accNum);
}
