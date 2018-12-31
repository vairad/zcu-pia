package cz.zcu.pia.revoloot.entities;

import java.util.Set;

/**
 * Rozhraní pro kontrolu nastavení parametrů objektů
 */
public interface IValidable {

    /**
     * Metoda validuje jednotlivé atributy a vrátí seznam všech, které jsou šptaně vyplněné
     * @return seznam chyb při validaci
     */
    Set<String> validate();

}
