package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;

public interface IMoveManager {

    /**
     * Zařadí pohyb ke zpracování
     *
     * pokud je výsledkem prázná množina - operace proběhla úspěšně
     *
     * @param move validovaný objekt pohybu
     * @return množina chybných polí elementu (empty = success)
     */
    void sendMoney(Move move, long customerD) throws MoveValidationException;


    void addTemplate(String templateName, Move move) throws MoveValidationException;

    /**
     * Meotda provede zpracování všech příkazů po splatnosti
     */
    void processMoves();
}
