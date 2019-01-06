package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.WELCOME)
public class Welcome extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.isUserInRole("CUSTOMER")){
            req.getRequestDispatcher(ServletNaming.CUSTOMER_DASHBOARD).forward(req, resp);
            return;
        }
        if(req.isUserInRole("BANKER")){
            req.getRequestDispatcher(ServletNaming.ADMIN_DASHBOARD).forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/WEB-INF/public/index.jsp").forward(req, resp);
    }

}
