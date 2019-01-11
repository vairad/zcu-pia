package cz.zcu.pia.revoloot.web.servlet.guest;


import cz.zcu.pia.revoloot.entities.Product;
import cz.zcu.pia.revoloot.manager.IProductManager;
import cz.zcu.pia.revoloot.manager.ProductManager;
import cz.zcu.pia.revoloot.web.ServletNaming;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(ServletNaming.PRODUCTS)
public class Products extends AbstractServlet {

    @Autowired
    private IProductManager productManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Produkty");

        List<Product> productList = productManager.getAllAvailableProducts();
        req.setAttribute("productList", productList);

        req.getRequestDispatcher("/WEB-INF/public/products.jsp").forward(req, resp);
    }

}
