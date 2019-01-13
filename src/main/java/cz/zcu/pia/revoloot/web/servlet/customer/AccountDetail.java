package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Pages;
import cz.zcu.pia.revoloot.manager.IAccountManager;
import cz.zcu.pia.revoloot.manager.IFormFiller;
import cz.zcu.pia.revoloot.web.ServletNaming;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_ACCOUNT + "/*")
public class AccountDetail extends CustomerBase {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    private IAccountManager accountManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.CUSTOMER_ACCOUNT);
        String accNoStr = req.getPathInfo().substring(1);

        Long accNo = Long.parseLong(accNoStr);

        Pages pages = formFiller.fillPages(req);

        Account acc = accountManager.loadAllAccountInfo(accNo, pages);

        req.setAttribute("account", acc);
        req.setAttribute("page", pages);
        prepareCustomerView(req);
        req.getRequestDispatcher("/WEB-INF/customer/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}