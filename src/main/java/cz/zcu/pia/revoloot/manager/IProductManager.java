package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Product;

import java.util.List;

/***
 * Služba pro zpracování produktů v aplikaci
 *
 * @author Radek VAIS
 */
public interface IProductManager {

    /**
     * Vrací všechny sjednatelné produkty k zobrazení
     * @return seznam všech sjednatelných produktů
     */
    List<Product> getAllAvailableProducts();
}
