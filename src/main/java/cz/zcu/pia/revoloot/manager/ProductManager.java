package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IProductDAO;
import cz.zcu.pia.revoloot.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductManager implements IProductManager {

    private final IProductDAO productDAO;

    @Autowired
    public ProductManager(IProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getAllAvailableProducts() {
        return productDAO.getAllAvailableProducts();
    }
}
