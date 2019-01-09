package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;
import cz.zcu.pia.revoloot.manager.IFormFiller;
import cz.zcu.pia.revoloot.manager.IMoveManager;
import cz.zcu.pia.revoloot.web.FormConfig;
import cz.zcu.pia.revoloot.web.ServletNaming;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.CUSTOMER_PAYMENT)
public class Payment extends CustomerBase {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    IMoveManager moveManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareCustomerView(req);
        req.getRequestDispatcher("/WEB-INF/customer/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareCustomerView(req);

        String forwardPage = "/WEB-INF/customer/payment.jsp";

        Move move = formFiller.fillMoveFromForm(req);
        User user = getLoggedUser();
        try {
            if (req.getParameter("submit").equals("pay")) {
                moveManager.sendMoney(move, user.getId());
                req.setAttribute("success", "Pohyb byl úspěšně zadán.");
                forwardPage = ServletNaming.WELCOME;
                resp.sendRedirect(ServletNaming.WELCOME);
            } else {
                String templateName = req.getParameter(FormConfig.TEMPLATE_NAME);
                moveManager.addTemplate(templateName, move);
                req.setAttribute("success", "Šablona byla úspěšně uložena.");
                forwardPage = ServletNaming.WELCOME;
            }
        } catch (MoveValidationException ex) {
            log("invalid move object");
            req.setAttribute("errors", ex.getErrors());
        }

        req.setAttribute("move", move);
        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }
}
