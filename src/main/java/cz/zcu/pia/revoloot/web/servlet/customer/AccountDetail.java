package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.manager.IAccountManager;
import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_ACCOUNT+"/*")
public class AccountDetail extends AbstractServlet {

    private IAccountManager accountManager;

    @Autowired
    public void setAccountDAO(IAccountManager accountDAO) {
        this.accountManager = accountDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accNoStr = req.getPathInfo().substring(1);

        Long accNo = Long.parseLong(accNoStr);
        Account acc = accountManager.loadAllAccountInfo(accNo);

        req.setAttribute("account", acc);

        req.getRequestDispatcher("/WEB-INF/customer/account.jsp").forward(req, resp);
    }

}