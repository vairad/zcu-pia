package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.entities.Move;
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
     * @return množina chybných polí dle form config
     * @see cz.zcu.pia.revoloot.web.FormConfig
     */
    @Override
    public Set<String> addMove(Move move) {
        Set<String> errors = move.validate(validator);
        if (errors.isEmpty()) {
            moveDAO.save(move);
        }
        return errors;
    }
}
