package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.web.ServletNaming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_PASSWORD)
public class UpdatePassword extends CustomerBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareCustomerView(req);
        req.getRequestDispatcher("/WEB-INF/customer/updatePassword.jsp").forward(req, resp);
    }
}
