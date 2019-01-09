package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.ExchangeRate;
import cz.zcu.pia.revoloot.entities.exceptions.ExchangeRateDoesNotExist;

public interface IExchangeDAO extends IGenericDAO<ExchangeRate> {

    double getExchchangeRate(Currency from, Currency to) throws ExchangeRateDoesNotExist;

}
