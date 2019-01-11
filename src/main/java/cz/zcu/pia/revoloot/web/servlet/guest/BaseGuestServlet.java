package cz.zcu.pia.revoloot.web.servlet.guest;

import cz.zcu.pia.revoloot.entities.Product;
import cz.zcu.pia.revoloot.manager.IProductManager;
import cz.zcu.pia.revoloot.web.servlet.AbstractServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class BaseGuestServlet extends AbstractServlet {

    @Autowired
    private IProductManager productManager;

    void loadProducts(HttpServletRequest request) {
        List<Product> productList = productManager.getAllAvailableProducts();
        request.setAttribute("productList", productList);
    }

}
