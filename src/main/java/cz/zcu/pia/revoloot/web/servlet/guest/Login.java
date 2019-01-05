package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.LOGIN)
public class Login extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.isUserInRole("USER")){
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/home");
            dispatcher.forward(req, resp);
        }
        req.setAttribute("title", "Přihlášení");
        req.getRequestDispatcher("/WEB-INF/public/login.jsp").forward(req, resp);
    }

}
