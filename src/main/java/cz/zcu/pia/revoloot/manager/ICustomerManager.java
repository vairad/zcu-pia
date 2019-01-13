package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.exceptions.CustomerValidationException;

import java.util.List;

/**
 * Manažer práce se zákaznickými účty
 */
public interface ICustomerManager {

    /**
     * Metoda pro registraci nového uživatelel
     *
     * @param save        příznak uložení
     * @param newCustomer objekt uživatele k uložení
     * @throws CustomerValidationException v případě validační chyby
     */
    void register(boolean save, Customer newCustomer) throws CustomerValidationException;

    /**
     * Metoda vrací stránkovaný seznam všech zákatzníků v aplikaci
     *
     * @param pages objekt stránkování
     * @return stránkovaný seznam
     */
    List<Customer> getAll(Pages pages);

    /**
     * Metoda vrací seznam všech účtů zákazníka
     *
     * @param customerID uivatelské id
     * @return seznam všech účtů
     */
    List<Account> getAllAccounts(long customerID);

    /**
     * Metoda vyhledá zákazníka dle přihlašovacího jména
     *
     * @param username přihlšovací jméno / login
     * @return nalezený zákazník / null
     */
    Customer getByUsername(String username);

    /**
     * Metoda vyhledá zákaznka  dle RBI {@link Customer#getRBI()}
     *
     * @param rbi revoloot banking index
     * @return zákazník / null
     */
    Customer getCustomerByRBI(Long rbi);

    /**
     * Metoda upraví údaje o zákkazníkovi
     *
     * @param save    příznak uložení
     * @param changes změny kontaktních údajů
     * @param user    uživatel, kterému je změna prováděna (a zároveň je jejím autorem)
     * @throws CustomerValidationException v případě chyby validace
     */
    void updateCustomerInfo(boolean save, ContactInfo changes, Customer user) throws CustomerValidationException;

    /**
     * Metoda uupraví údaje celého uživatele
     *
     * @param save    příznak uložení
     * @param changes zěmny k uložení
     * @param user    objekt uživatele ke změně
     * @param banker  autor změny
     * @throws CustomerValidationException v případě validační chyby
     */
    void updateCustomer(boolean save, Customer changes, Customer user, User banker) throws CustomerValidationException;

    /***
     * MEtoda odstraní zákazníka ze systému
     *
     * @param customer zákazník k odstranění
     * @param banker autor změny
     */
    void removeCustomer(Customer customer, User banker);
}
