package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.AccountAddress;
import cz.zcu.pia.revoloot.entities.Pages;
import cz.zcu.pia.revoloot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * DAO pro práci s účty uživatelů
 *
 * @author Radek VAIS
 */
public interface IAccountDAO extends IGenericDAO<Account> {

    /**
     * Metoda vyhledá účet podle jeho čísla
     *
     * @param accNo číslo místního účtu (accountAddress.number)
     * @return objekt čísla účtu / null pokud neexistuje
     */
    Account findByAccountNumber(long accNo);

    /**
     * Nalezne seznam účtů dle ID uživatele
     *
     * @param customerID userID daného zákazníka
     * @return seznam účtů / null, pokud nebyl zádný nalezen
     */
    List<Account> findByUserId(long customerID);

    /**
     * Metoda ověří, že daný uživatel je majitelem účtu a vrátí nalezený objekt účtu.
     * Pokud takový účet neexistuje je vráceno null
     * @param userId id uřivatele
     * @param accNo číslo hledaného účtu
     * @return účet / null pokud nemá práva
     */
    Account checkAccount(Long userId, Long accNo);

    /**
     * Metoda najde nejvyšší přidělené číslo
     * @return max saved account number
     */
    Long findMaxAccountNumber();
}
