package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Banker;

import java.util.List;

/**
 * Manager pro práci s bankéři
 *
 * @author Radek Vais
 */
public interface IBankerManager {

    /**
     * Seznam všech veřejně viditelných bankéřů
     *
     * @return seznam veřejných bankéřů
     */
    List<Banker> getPublicBankersList();
}
