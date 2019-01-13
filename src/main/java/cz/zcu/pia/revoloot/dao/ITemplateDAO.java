package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.entities.Template;

import java.util.List;


/**
 * DAO pro práci s šablonami
 *
 * @author Radek VAIS
 */
public interface ITemplateDAO extends IGenericDAO<Template> {

    /**
     * Meotda vyhledá všechny šablony daného uživatele
     * @param id user / customer ID
     * @return seznam šablon
     */
    List<Template> getAllTemplatesByUser(Long id);

    /**
     * Metoda najde šablonu dle jména
     * @param name název šablony
     * @return objekt šablony / null
     */
    Template fidByName(String name);
}
