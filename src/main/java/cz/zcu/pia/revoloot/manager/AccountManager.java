package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.entities.Account;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountManager implements IAccountManager {


    @Autowired
    public IAccountDAO accountDAO;

    @Override
    public Account loadAllAccountInfo(long accNum) {
        Account a = accountDAO.findByAccountNumber(accNum);
        Hibernate.initialize(a.getMoves());
        return a;
    }
}
