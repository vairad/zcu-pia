package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.manager.IBankerManager;
import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CONTACTS)
public class Contacts extends AbstractServlet {

    @Autowired
    public IBankerManager bankerManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("title", "Kontakty");
        req.setAttribute("bankers", bankerManager.getPublicBankersList());

        req.getRequestDispatcher("/WEB-INF/public/contacts.jsp").forward(req, resp);
    }

}
