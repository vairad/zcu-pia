package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.ExchangeRate;

public interface IExchangeDAO extends IGenericDAO<ExchangeRate> {

    double getExchchangeRate(Currency from, Currency to);

}
