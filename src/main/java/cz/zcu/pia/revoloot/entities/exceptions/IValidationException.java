package cz.zcu.pia.revoloot.entities.exceptions;

import java.util.Set;

/**
 * Rozhraní pro třídy ValidationException, deklarující metodu pro zjištění chyb na objektu.
 *
 * @author Radek VAIS
 */
public interface IValidationException {

    /**
     * Metoda pro zjištění chyb na objektu, který validátor ověřoval.
     *
     * @see cz.zcu.pia.revoloot.web.FormConfig
     * @return řetězec představující množinu chyb
     */
    String getErrors();
}
