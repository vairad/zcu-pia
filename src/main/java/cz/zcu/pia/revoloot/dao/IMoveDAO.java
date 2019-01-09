package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Move;

import java.util.List;

/**
 * DAO pro práci s pohyby na účtech
 *
 * @author Radek VAIS
 */
public interface IMoveDAO extends IGenericDAO<Move> {

    List<Move> getMovesToProcess(int limit);
}
