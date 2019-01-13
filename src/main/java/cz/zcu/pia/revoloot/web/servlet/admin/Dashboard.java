package cz.zcu.pia.revoloot.web.servlet.admin;


import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Pages;
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
import java.util.List;

@WebServlet(ServletNaming.ADMIN_DASHBOARD)
public class Dashboard extends AbstractServlet {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    public ICustomerManager customerManager;

    @Autowired
    public void setCustomerManager(ICustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.ADMIN_DASHBOARD);
        Pages pages = formFiller.fillPages(req);
        List<Customer> customerList = customerManager.getAll(pages);
        req.setAttribute("customerList", customerList);
        req.setAttribute("page", pages);
        req.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.ADMIN_DASHBOARD);
        doGet(req, resp);
    }

}
