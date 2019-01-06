package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class CustomerBase extends AbstractServlet {

    @Autowired
    public ICustomerManager customerManager;

    void prepareCustomerView(HttpServletRequest req) {
        String userName = req.getUserPrincipal().getName();
        if(userName != null){
            Customer customer = customerManager.getByUsername(userName);
            if(customer != null){
                req.setAttribute("accountList", customerManager.getAllAccounts(customer.getId()));
            }
        }
    }
}
