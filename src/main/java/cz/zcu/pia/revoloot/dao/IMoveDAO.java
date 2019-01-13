package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.Pages;

import java.util.List;

/**
 * DAO pro práci s pohyby na účtech
 *
 * @author Radek VAIS
 */
public interface IMoveDAO extends IGenericDAO<Move> {

    /**
     * Funkce najde seznam pohybů ke zpracování bankou až do výše limitu
     * @param limit maximální počet zpracovaných pohybů
     * @return seznam pohybů ke zpracování
     */
    List<Move> getMovesToProcess(int limit);

    /**
     * Funkce najde seznam pohybů pro daný účet dle zvoleného stránkování
     * @param a účet příslušnosti pohybů
     * @param pages element stránkování
     * @return stránkovyný seznam pohybů na zvoleném účtu
     */
    List<Move> findMovesForAccount(Account a, Pages pages);
}
