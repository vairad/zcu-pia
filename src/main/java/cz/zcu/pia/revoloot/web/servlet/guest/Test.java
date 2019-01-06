package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.manager.ICustomerManager;
import cz.zcu.pia.revoloot.utils.BasicValidator;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@WebServlet("/boo")
public class Test extends AbstractServlet {

    @Autowired
    private ICustomerManager customerManager;

    private static Customer prepareCustomer() {
        Address address = new Address();
        address.setCity("Autommat");
        address.setHouseNo("15");
        address.setStreet("Mulajova");
        address.setState(State.SVK);

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress(address);
        contactInfo.setEmail("boo@voo.doo");
        contactInfo.setPhone(123456789);

        Customer customer = new Customer();
        customer.setName("Marek");
        customer.setSurname("Prijmenak");
        customer.setContactInfo(contactInfo);
        customer.setGender(Gender.MALE);

        return customer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer c = customerManager.);
        req.setAttribute("accounts", c.getAccountList());


        req.getRequestDispatcher("/WEB-INF/customer/index.jsp").forward(req, resp);
    }
}
