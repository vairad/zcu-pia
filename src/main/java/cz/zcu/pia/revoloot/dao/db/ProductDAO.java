package cz.zcu.pia.revoloot.dao.db;

import cz.zcu.pia.revoloot.dao.IProductDAO;
import cz.zcu.pia.revoloot.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Úložiště pro Produkty sjednatelné v aplikaci.
 *
 * @author Radek VAIS
 */
@Repository
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

    private Logger logger = LoggerFactory.getLogger(ProductDAO.class.getName());

    ProductDAO() {
        super(Product.class);
    }

    /**
     * Konsturuktor pro testy
     * @param em DB kontext
     */
    public ProductDAO(EntityManager em) {
        super(em, Product.class);
    }

    /**
     * Meotda vrací všechny produkty uložené v úložišti.
     * @param available příznak dostupnosti produktů
     * @return seznam všech produktů
     */
    @Override
    public List<Product> getAllProducts(boolean available) {
        logger.info("Find products availability " + available);

        TypedQuery<Product> q = em.createQuery("SELECT prod FROM Product prod WHERE prod.available = :available", Product.class);
        q.setParameter("available", available);
        try {
            List<Product> allProducts = q.getResultList();
            logger.info("Some products found " + allProducts.size());
            return allProducts;
        } catch (NoResultException e) {
            logger.debug("No account found for availability flag: " + available);
            //no result found
            return null;
        }
    }



    /**
     * Metoda vrací seznam všech dostupných produktů
     * @return seznam všech dostupných (skednatelnýhc produktů)
     */
    @Override
    public List<Product> getAllAvailableProducts() {
        return getAllProducts(true);
    }
}
