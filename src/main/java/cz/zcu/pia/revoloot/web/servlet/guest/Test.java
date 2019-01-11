package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/boo")
public class Test extends BaseGuestServlet {

    @Autowired
    private ICustomerManager customerManager;

//TODO remove!!!!!!!!!!!!!§§

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadProducts(req);
        req.setAttribute("accountList", customerManager.getAllAccounts(1));

        req.getRequestDispatcher("/WEB-INF/customer/index.jsp").forward(req, resp);
    }
}
