package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.Customer;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.Template;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;

import java.util.List;

public interface IMoveManager {

    /**
     * Zařadí pohyb ke zpracování
     * <p>
     * pokud je výsledkem prázná množina - operace proběhla úspěšně
     *
     * @param move validovaný objekt pohybu
     * @return množina chybných polí elementu (empty = success)
     */
    void sendMoney(boolean save, Move move, long customerD) throws MoveValidationException;

    /**
     * Meotda provede zpracování všech příkazů po splatnosti do výče splatnosti
     *
     * @param limit počet zpracovaných pohybů v jednom kroku
     */
    void processMoves(int limit);

    List<Template> getTemplatesByUser(Long id);

    void addTemplate(boolean save, String templateName, Move move, Customer customerId) throws MoveValidationException;

    Template loadTemplate(Long templateID, Long id);

    void removeTemplate(Long templateId, Long id);
}
