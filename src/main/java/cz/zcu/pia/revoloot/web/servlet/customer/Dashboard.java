package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.web.ServletNaming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_DASHBOARD)
public class Dashboard extends CustomerBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.CUSTOMER_DASHBOARD);
        prepareCustomerView(req);
        req.getRequestDispatcher("/WEB-INF/customer/index.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}