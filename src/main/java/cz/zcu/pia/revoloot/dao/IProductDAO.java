package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Product;

import java.util.List;


/**
 * DAO pro práci s produkty banky
 *
 * @author Radek VAIS
 */
public interface IProductDAO extends IGenericDAO<Product> {

    /**
     * Meotda vrací všechny produkty uložené v úložišti.
     * @param available příznak dostupnosti produktů
     * @return seznam všech produktů
     */
    List<Product> getAllProducts(boolean available);

    /**
     * Metoda vrací seznam všech dostupných produktů
     * @return seznam všech dostupných (skednatelnýhc produktů)
     */
    List<Product> getAllAvailableProducts();
}
