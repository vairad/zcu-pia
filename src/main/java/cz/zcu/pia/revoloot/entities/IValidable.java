package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IValidator;

import java.util.Set;

/**
 * Rozhraní pro kontrolu nastavení parametrů objektů
 */
public interface IValidable {

    /**
     * Metoda validuje jednotlivé atributy a vrátí seznam všech, které jsou šptaně vyplněné
     * @return seznam chyb při validaci
     */
    Set<String> validate(IValidator validator);

    /**
     * Metoda vrací plnou množinu všech chyb objektu.
     * @return množina všech validovaných polí.
     */
    Set<String> errorFields();

}
