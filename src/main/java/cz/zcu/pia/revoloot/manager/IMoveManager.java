package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.Template;
import cz.zcu.pia.revoloot.exceptions.MoveValidationException;

import java.util.List;

/**
 * Služba pro zpracování pohybů a jejich šablon na účtech
 */
public interface IMoveManager {

    /**
     * Zařadí pohyb ke zpracování
     * <p>
     * pokud je výsledkem prázná množina - operace proběhla úspěšně
     *
     * @param move validovaný objekt pohybu
     * @throws MoveValidationException v případě chby validace
     */
    void sendMoney(boolean save, Move move, long customerD) throws MoveValidationException;

    /**
     * Meotda provede zpracování všech příkazů po splatnosti do výče splatnosti
     *
     * @param limit počet zpracovaných pohybů v jednom kroku
     */
    void processMoves(int limit);

    /***
     * Metoda načte šablony pro daného uživatelel
     * @param id user id
     * @return seznam šablon uživatelel
     */
    List<Template> getTemplatesByUser(Long id);

    /**
     * Metoda uloží šablonu k dalšímu použítí.
     * <p>
     * V případě ukládání šablony se stejným názvem je provedena úprava stáváající
     *
     * @param save         příznak uložení
     * @param templateName název šablony
     * @param move         pohyb, který má být do šablny  vložen
     * @param customerId   uživatel, kterému šablona patří
     * @throws MoveValidationException v případě, že pohyb je špatně vyplněn
     */
    void addTemplate(boolean save, String templateName, Move move, Customer customerId) throws MoveValidationException;

    /**
     * Načte šablonu dle ID a ověří, že uživatel dle id je vlastníkem
     *
     * @param templateID id šablony
     * @param id         uživatele, pro ověření vlastnictví
     * @return objekt šablony / null pokud není rebo nesedí práva
     */
    Template loadTemplate(Long templateID, Long id);

    /**
     * Metoda odstraní šablonu ze systému
     *
     * @param templateId id šablony k odstranění
     * @param id         id vlastníka šablony
     */
    void removeTemplate(Long templateId, Long id);
}
