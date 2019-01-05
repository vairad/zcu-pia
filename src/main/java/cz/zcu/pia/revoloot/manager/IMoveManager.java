package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Move;

import java.util.Set;

public interface IMoveManager {

    /**
     * Zařadí pohyb ke zpracování
     *
     * pokud je výsledkem prázná množina - operace proběhla úspěšně
     *
     * @param move validovaný objekt pohybu
     * @return množina chybných polí elementu (empty = success)
     */
    Set<String> addMove(Move move);
}
