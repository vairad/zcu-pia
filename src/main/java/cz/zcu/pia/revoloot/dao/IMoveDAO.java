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

    List<Move> getMovesToProcess(int limit);

    List<Move> findMovesForAccount(Account a, Pages pages);
}
