package cz.zcu.pia.revoloot.web.servlet.customer;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.Template;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.exceptions.MoveValidationException;
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
import java.util.List;

@WebServlet(ServletNaming.CUSTOMER_PAYMENT + "/*")
public class Payment extends CustomerBase {

    @Autowired
    private IFormFiller formFiller;

    @Autowired
    IMoveManager moveManager;


    private void prepareTemplates(HttpServletRequest req) {
        log(ServletNaming.CUSTOMER_PAYMENT);
        User user = getLoggedUser();
        List<Template> templateList = moveManager.getTemplatesByUser(user.getId());
        req.setAttribute("templateList", templateList);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(ServletNaming.CUSTOMER_PAYMENT);
        String templateId = null;
        if (req.getPathInfo() != null) {
            templateId = req.getPathInfo().substring(1);
        }

        if (templateId != null) {
            try {
                Long templateID = Long.parseLong(templateId);

                if ("true".equals(req.getParameter("d"))) {
                    //delete template
                    moveManager.removeTemplate(templateID, getLoggedUser().getId());
                } else {
                    //load template
                    Template template = moveManager.loadTemplate(templateID, getLoggedUser().getId());
                    if (template != null) {
                        req.setAttribute("move", template.getMove());
                        req.setAttribute(FormConfig.TEMPLATE_NAME, template.getName());
                    }
                }
            } catch (NumberFormatException ex) {
                log("incorrect template id.");
            }
        }

        prepareTemplates(req);
        prepareCustomerView(req);
        prepareTuringQuestion(req);
        req.getRequestDispatcher("/WEB-INF/customer/payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareCustomerView(req);

        String forwardPage = "/WEB-INF/customer/payment.jsp";

        String templateName = req.getParameter(FormConfig.TEMPLATE_NAME);
        Move move = formFiller.fillMoveFromForm(req);
        User user = getLoggedUser();
        boolean save = checkTuringQuestion(req);
        try {
            if (req.getParameter("submit").equals("pay")) {
                moveManager.sendMoney(save, move, user.getId());
                req.setAttribute("success", "Pohyb byl úspěšně zadán.");
                forwardPage = ServletNaming.WELCOME;
            } else {
                if(!(user instanceof Customer)){
                    log("Unnable to add customer as template owner");
                    return;
                }
                moveManager.addTemplate(save, templateName, move, (Customer) user);
                req.setAttribute("success", "Šablona byla úspěšně uložena.");
                forwardPage = ServletNaming.WELCOME;
            }
        } catch (MoveValidationException ex) {
            log("invalid move object");
            req.setAttribute("errors", ex.getErrors());

        }
        req.setAttribute(FormConfig.TEMPLATE_NAME, templateName);
        prepareTemplates(req);
        prepareTuringQuestion(req);
        req.setAttribute("move", move);
        req.getRequestDispatcher(forwardPage).forward(req, resp);
    }
}
