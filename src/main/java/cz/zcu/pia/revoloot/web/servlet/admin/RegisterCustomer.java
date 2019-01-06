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

@WebServlet(ServletNaming.ADMIN_REGISTER)
public class RegisterCustomer extends AbstractServlet {

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
        req.getRequestDispatcher("/WEB-INF/admin/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //naplň objekt daty z formuláře
        Customer customer = formFiller.fillCustomerFromForm(req);
        //proveď operaci
        boolean success = false;
        try {
            customerManager.register(customer);
            success = true;

        } catch (CustomerValidationException e) {
            req.setAttribute("customer", customer);
            req.setAttribute("error", "Uživatel nemohl být vytvořen.");
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("/WEB-INF/admin/createCustomer.jsp").forward(req, resp);
        }
        if (success) {
            req.setAttribute("success", "Zákazník byl vytvořen.");
            req.getRequestDispatcher("/home").forward(req, resp);
        }
    }

}
