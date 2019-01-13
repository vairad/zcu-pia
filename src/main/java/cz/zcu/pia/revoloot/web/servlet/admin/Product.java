package cz.zcu.pia.revoloot.web.servlet.admin;

import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.exceptions.AccountCreateException;
import cz.zcu.pia.revoloot.manager.IAccountManager;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.manager.IFormFiller;
import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.ADMIN_PRODUCT + "/*")
public class Product extends AbstractServlet {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    private IAccountManager accountManager;

    @Autowired
    private ICustomerManager customerManager;


    private void loadCustomerFromRBI(HttpServletRequest request, Long rbi) {
        log(ServletNaming.ADMIN_DASHBOARD);
        Customer selectedCustomer = customerManager.getCustomerByRBI(rbi);
        if (selectedCustomer != null) {
            request.setAttribute("selectedCustomer", selectedCustomer);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.ADMIN_PRODUCT);
        String rbiStr = null;
        if (req.getPathInfo() != null) {
            rbiStr = req.getPathInfo().substring(1);
        }
        try {
            Long rbi = Long.parseLong(rbiStr);
            loadCustomerFromRBI(req, rbi);
        } catch (NumberFormatException | NullPointerException ex) {
            log("wrong url parameter RBI: " + rbiStr);
        }

        loadProducts(req);
        prepareTuringQuestion(req);
        req.getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.ADMIN_PRODUCT);
        String errors = "";
        boolean turing = checkTuringQuestion(req);
        if (!turing) {
            errors += "turing, ";
        }

        Long accountType = formFiller.fillAccountType(req);
        Long rbi = formFiller.fillRBI(req);
        Currency currency = formFiller.fillCurrency(req);

        try {
            accountManager.createNewAccount(turing, accountType, rbi, currency);
            req.setAttribute("success", "Účet byl úspěšně založen.");
            req.getRequestDispatcher(ServletNaming.WELCOME).forward(req, resp);
            return;
        } catch (AccountCreateException e) {
            log("wrong fields");
            req.setAttribute("errors", errors + e.getErrors());
            req.setAttribute("error", "Problém v polích: "+errors + e.getErrors());
        }

        if (rbi != null) {
            loadCustomerFromRBI(req, rbi);
        }

        loadProducts(req);
        prepareTuringQuestion(req);
        req.getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(req, resp);
    }
}
