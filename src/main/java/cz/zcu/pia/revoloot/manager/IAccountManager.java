package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.Pages;
import cz.zcu.pia.revoloot.exceptions.AccountCreateException;

public interface IAccountManager {

    Account loadAllAccountInfo(long accNum, Pages pages);

    void createNewAccount(boolean turing, Long accountType, Long rbi, Currency currency) throws AccountCreateException;
}
