package cz.zcu.pia.revoloot.web.servlet.admin;


import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
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

@WebServlet(ServletNaming.ADMIN_CUSTOMER + "/*")
public class UpdateCustomer extends AbstractServlet {

    private IFormFiller formFiller;
    private ICustomerManager customerManager;

    @Autowired
    public void setFormFiller(IFormFiller formFiller) {
        this.formFiller = formFiller;
    }

    @Autowired
    public void setCustomerManager(ICustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String rbiStr = req.getPathInfo().substring(1);
            Long rbi = Long.parseLong(rbiStr);
            Customer customer = customerManager.getCustomerByRBI(rbi);
            if ("true".equals(req.getParameter("d")) && req.isUserInRole("BANKER")) {
                //delete customer
                customerManager.removeCustomer(customer, getLoggedUser());
                req.setAttribute("success", "Zákazník byl odstraněn ze systému. ");
                req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
            } else {
                prepareTuringQuestion(req);
                req.setAttribute("customer", customer);
                req.getRequestDispatcher("/WEB-INF/admin/updateCustomer.jsp").forward(req, resp);
            }
        } catch (NumberFormatException | NullPointerException ex) {
            req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = null;
        try {
            String rbiStr = req.getPathInfo().substring(1);
            Long rbi = Long.parseLong(rbiStr);
            customer = customerManager.getCustomerByRBI(rbi);
        } catch (NumberFormatException | NullPointerException ex) {
            req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
            return;
        }

        if (customer == null) {
            req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
            return;
        }

        Customer changes = formFiller.fillCustomerFromForm(req);
        boolean turing = checkTuringQuestion(req);
        try {
            customerManager.updateCustomer(turing, changes, customer, getLoggedUser());
            req.setAttribute("success", "Údaje byly úspěšně změněny.");
            req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
            return;
        } catch (CustomerValidationException e) {
            req.setAttribute("errors", e.getErrors());
        }

        changes.setId(customer.getId());
        req.setAttribute("customer", changes);
        prepareTuringQuestion(req);
        req.getRequestDispatcher("/WEB-INF/admin/updateCustomer.jsp").forward(req, resp);
    }
}
