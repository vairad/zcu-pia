package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.dao.db.ProductDAO;
import cz.zcu.pia.revoloot.entities.EntityFactory;
import cz.zcu.pia.revoloot.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductDAOTest extends DaoTest {

    private static IProductDAO productDAO;

    // region preprare tests
    @BeforeAll
    static void setUpDependencies() {
        productDAO = new ProductDAO(em);
    }

    @Test
    void testSave()
    {
        Product p = EntityFactory.createProduct();
        productDAO.save(p);
        assertNotEquals(0L, p.getId());
    }
}
