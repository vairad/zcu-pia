package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Banker;

import java.util.List;

/**
 * DAO pro práci s bankéři
 *
 * @author Radek VAIS
 */
public interface IBankerDAO extends IGenericDAO<Banker> {

    /**
     * Meotda vyhledá vrátí všechny aktivní existující veřejné bankéře. ({@link Banker#isSecret()})
     *
     * @return seznam všech bankéřů
     * @see Banker
     */
    List<Banker> loadAllPublicBankers();

    /**
     * Meotda vyhledá vrátí všechny aktivní existující soukromé bankéře. ({@link Banker#isSecret()})
     *
     * @return seznam všech bankéřů
     * @see Banker
     */
    List<Banker> loadAllSecretBankers();

}
