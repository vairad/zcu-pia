package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Product;

import java.util.List;

public interface IProductManager {

    /**
     * Vrací všechny sjednatelné produkty k zobrazení
     * @return seznam všech sjednatelných produktů
     */
    List<Product> getAllAvailableProducts();
}
