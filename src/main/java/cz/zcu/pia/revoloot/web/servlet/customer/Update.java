package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.ContactInfo;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.manager.IFormFiller;
import cz.zcu.pia.revoloot.web.ServletNaming;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_UPDATE)
public class Update extends CustomerBase {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    private ICustomerManager customerManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.CUSTOMER_UPDATE);
        prepareCustomerView(req);
        prepareTuringQuestion(req);
        req.getRequestDispatcher("/WEB-INF/customer/update.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.CUSTOMER_UPDATE);
        ContactInfo changes = formFiller.fillContactInfoFromForm(req);
        Customer customer = null;
        User user = getLoggedUser();
        if(user instanceof Customer) {
            try {
                customer = (Customer) user;
                boolean turing = checkTuringQuestion(req);
                customerManager.updateCustomerInfo(turing, changes, customer);
                req.setAttribute("success", "Údaje byly upraveny.");
                req.getRequestDispatcher(ServletNaming.CUSTOMER_PROFILE).forward(req,resp);
                return;
            }catch (CustomerValidationException ex){
                req.setAttribute("errors", ex.getErrors());
            }
        }

        prepareTuringQuestion(req);
        prepareCustomerView(req);
        if(customer != null){
            customer.setContactInfo(changes);
        }
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/customer/update.jsp").forward(req, resp);
    }
}
