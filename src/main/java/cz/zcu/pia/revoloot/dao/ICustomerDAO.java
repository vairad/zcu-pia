package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Pages;

import java.util.List;

/**
 * DAO pro práci s uživateli
 *
 * @author Radek VAIS
 */
public interface ICustomerDAO extends IGenericDAO<Customer> {

    /**
     * Metoda vyhledá uživatele dle čísla účtu.
     *
     * @param accountNumber reprezentace čísla účtu.
     * @return Objekt User / null v případě chyby, nebo neexistujícího záznamu.
     */
    Customer findByAccountNumber(String accountNumber);

    /**
     * Vyhledá uživatele dle uživatelského jména.
     * @param username uživatelské jméno
     * @return nalezený objekt / null
     */
    Customer findByUsername(String username);

    /**
     * Metoda připraví seznam všech uživatelů
     * @return seznam všech uživatelů
     * @param pages
     */
    List<Customer> findAllCustomers(Pages pages);

    /**
     * Metoda vyhledá úživatele dle RBI
     * @param rbi zákaznické číslo
     * @return Customer nebo null, pokud není nalezen.
     */
    Customer findByRBI(Long rbi);
}
