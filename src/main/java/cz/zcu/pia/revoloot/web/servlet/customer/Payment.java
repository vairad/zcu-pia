package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;
import cz.zcu.pia.revoloot.manager.IFormFiller;
import cz.zcu.pia.revoloot.manager.IMoveManager;
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

        Move move = formFiller.fillMoveFromForm(req);

        try {
            if (req.getParameter("submit").equals("pay")) {
                moveManager.addMove(move);
                req.setAttribute("success", "Pohyb byl úspěšně zadán.");
                req.getRequestDispatcher(ServletNaming.WELCOME).forward(req, resp);
                return;
            } else {
                moveManager.addTemplate(move);
                req.setAttribute("success", "Šablona byla úspěšně uložena.");
                req.getRequestDispatcher(ServletNaming.WELCOME).forward(req, resp);
                return;
            }
        }catch (MoveValidationException ex){
            log("invalid move object");
            req.setAttribute("errors", ex.getErrors());
        }

        req.setAttribute("move", move);
        req.getRequestDispatcher("/WEB-INF/customer/payment.jsp").forward(req, resp);
    }
}
