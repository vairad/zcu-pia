package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.dao.IProductDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.utils.IBankNumbers;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountManager implements IAccountManager {

    @Autowired
    private IMoveDAO moveDAO;

    @Autowired
    public IAccountDAO accountDAO;

    @Autowired
    public ICustomerDAO customerDAO;

    @Autowired
    public IProductDAO productDAO;

    @Autowired
    public IBankNumbers bankNumbers;

    @Override
    public Account loadAllAccountInfo(long accNum, Pages pages) {
        Account a = accountDAO.findByAccountNumber(accNum);
        a.setMoves(moveDAO.findMovesForAccount(a, pages));
       // Hibernate.initialize(a.getMoves());
        return a;
    }

    @Override
    public void createNewAccount(boolean save, Long accountType, Long rbi, Currency currency) throws AccountCreateException {
        String errors = "";
        Product product = productDAO.findOne(accountType);
        if (product == null) {
            errors += FormConfig.PRODUCT + ", ";
            save = false;
        }
        Customer customer = customerDAO.findByRBI(rbi);
        if (customer == null) {
            errors += FormConfig.RBI+", ";
            save = false;
        }

        if(currency == null){
            errors += FormConfig.CURRENCY+", ";
            save = false;
        }

        if(save){
            Long maxAccNo = accountDAO.findMaxAccountNumber();
            Long newAccNo = bankNumbers.getNewAccNum(maxAccNo);

            AccountAddress accountAddress = new AccountAddress();
            accountAddress.setNumber(newAccNo);
            accountAddress.setBankCode(bankNumbers.getBankCode());

            Account account = new Account();
            account.setAccountInfo(accountAddress);
            account.setCustomer(customer);
            account.setProduct(product);
            account.setCurrency(currency);

            accountDAO.save(account);
        }else {
            throw new AccountCreateException(errors);
        }
    }
}
