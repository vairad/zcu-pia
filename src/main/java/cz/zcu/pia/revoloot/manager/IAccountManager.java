package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Pages;
import cz.zcu.pia.revoloot.exceptions.AccountCreateException;

/**
 * Správce operací s bankovními účty zákazníků.
 *
 * @author Radek VAIS
 */
public interface IAccountManager {

    /**
     * Metoda načte inofmace o účtu se stránkovanými pohyby dle pages
     *
     * @param accNum číslo účtu
     * @param pages obbjekt stránkování
     * @return informace o účtu se stránkou pohybů
     */
    Account loadAllAccountInfo(long accNum, Pages pages);

    /**
     * Metoda vytvoří nový účet uživateli dle RBI revoloot banking index
     *
     * @param turing příznak uložení
     * @param accountType {@link cz.zcu.pia.revoloot.entities.Product} typ účtu
     * @param rbi zákaznický index {@link Customer#getRBI()}
     * @param currency měna účtu
     * @throws AccountCreateException v případě nekorektních dat
     */
    void createNewAccount(boolean turing, Long accountType, Long rbi, Currency currency) throws AccountCreateException;
}
