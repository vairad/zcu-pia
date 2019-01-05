package cz.zcu.pia.revoloot.web.servlet.guest;


import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.PRODUCTS)
public class Products extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Produkty");

        //todo load products

        req.getRequestDispatcher("/WEB-INF/public/products.jsp").forward(req, resp);
    }

}
