package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Account;

public interface IAccountManager {

    Account loadAllAccountInfo(long accNum);
}
