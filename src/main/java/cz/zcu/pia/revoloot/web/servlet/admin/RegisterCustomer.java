package cz.zcu.pia.revoloot.web.servlet.admin;


import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/register")
public class RegisterCustomer extends AbstractServlet {

    private ICustomerManager customerManager;

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
        //TODO void
    }

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute(ERROR_ATTRIBUTE, err);
//        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
