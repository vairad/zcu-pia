package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Banker;

import java.util.List;

/**
 * DAO pro práci s bankéři
 */
public interface IBankerDAO {

    /**
     * Meotda vyhledá vrátí všechny aktivní existující veřejné bankéře. ({@link Banker#isSecret()})
     *
     * @see Banker
     * @return seznam všech bankéřů
     */
    List<Banker> loadAllPublicBankers();

    /**
     * Meotda vyhledá vrátí všechny aktivní existující soukromé bankéře. ({@link Banker#isSecret()})
     *
     * @see Banker
     * @return seznam všech bankéřů
     */
    List<Banker> loadAllSecretBankers();

}
