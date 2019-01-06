package cz.zcu.pia.revoloot.web.servlet.admin;


import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.entities.exceptions.CustomerValidationException;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@WebServlet(ServletNaming.ADMIN_REGISTER)
public class RegisterCustomer extends AbstractServlet {

    private ICustomerManager customerManager;

    @Autowired
    public void setCustomerManager(ICustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/createCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //naplň objekt daty z formuláře
        Customer customer = new Customer();
        customer.setName(req.getParameter(FormConfig.NAME));
        customer.setSurname(req.getParameter(FormConfig.SURNAME));
        String date = req.getParameter(FormConfig.BIRTH_DATE);
        try {
            customer.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (ParseException |NullPointerException e) {
            log("Date could not be parsed");
        }

        customer.setCardID(req.getParameter(FormConfig.CARD_ID));
        customer.setPersonIDSmart(req.getParameter(FormConfig.PERSON_ID));
       // customer.setGender();

        //proveď operaci
        try {
            customerManager.register(customer);
        } catch (CustomerValidationException e) {
            req.setAttribute("customer", customer);
            req.setAttribute("error", "Uživatel nemohl být vytvořen.");
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("/WEB-INF/admin/createCustomer.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("success", "Zákazník byl vytvořen.");
        req.getRequestDispatcher("/WEB-INF/home").forward(req, resp);
    }

    private void errorDispatch(String err, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute(ERROR_ATTRIBUTE, err);
//        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
