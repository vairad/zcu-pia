package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;
import cz.zcu.pia.revoloot.utils.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Service
public class MoveManager implements IMoveManager {

    private final IValidator validator;

    private final IMoveDAO moveDAO;

    @Autowired
    public MoveManager(IMoveDAO moveDAO, IValidator validator) {
        this.moveDAO = moveDAO;
        this.validator = validator;
    }

    /**
     * Metoda zvaliduje a zařadí objekt pohybu ke zpracování
     *
     * @param move validovaný objekt pohybu
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void addMove(Move move) throws MoveValidationException {
        Set<String> errors = move.validate(validator);
        if (errors.isEmpty()) {
            moveDAO.save(move);
            return;
        }
        throw new MoveValidationException(errors);
    }

    /**
     * Metoda uloží zvalidovaný pohyb jako šablonu pro další použití.
     * @param move pohyb k uložení
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void addTemplate(Move move) throws MoveValidationException {
        Set<String> errors = move.validate(validator);
        if (errors.isEmpty()) {
            //moveDAO.save(move);
            return;
        }
        throw new MoveValidationException(errors);
    }
}
