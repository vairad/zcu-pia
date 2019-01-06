package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Customer;

import java.util.List;

public interface ICustomerDAO extends IGenericDAO<Customer> {

    /**
     * Metoda vyhledá uživatele dle čísla účtu.
     *
     * @param accountNumber reprezentace čísla účtu.
     * @return Objekt User / null v případě chyby, nebo neexistujícího záznamu.
     */
    Customer findByAccountNumber(String accountNumber);

    /**
     * Metoda vyhledá uživatele dle zákaznického ID
     *
     * @param customerID reprezentace zákkaznického ID
     * @return Objekt User / null v případě chyby, nebo neexistujícího záznamu.
     */
    Customer findByCustomerId(String customerID);

    List<Customer> findAllCustomers();
}
