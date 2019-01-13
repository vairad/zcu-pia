package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.ExchangeRate;
import cz.zcu.pia.revoloot.exceptions.ExchangeRateDoesNotExist;

/**
 * DAO pro práci s kurzy
 *
 * @author Radek VAIS
 */
public interface IExchangeDAO extends IGenericDAO<ExchangeRate> {

    /**
     * Meotda najde kurz pro kombinaci měn.
     *
     * @param from zdrojová měna
     * @param to cílová měna
     * @return double kurz
     * @throws ExchangeRateDoesNotExist pokud nebyl kurz nalezen
     */
    double getExchangeRate(Currency from, Currency to) throws ExchangeRateDoesNotExist;

}
