package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Currency;

public interface IAccountManager {

    Account loadAllAccountInfo(long accNum);

    void createNewAccount(boolean turing, Long accountType, Long rbi, Currency currency) throws AccountCreateException;
}
