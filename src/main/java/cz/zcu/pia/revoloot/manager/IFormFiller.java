package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Spužba pro naplění datvýc bjektů z requestu naplněného formuláři dle {@link cz.zcu.pia.revoloot.web.FormConfig}
 *
 * @see cz.zcu.pia.revoloot.web.FormConfig
 * @author Radek VAIS
 */
public interface IFormFiller {

    /**
     * Naplní objekt Customer
     * @param req request z formuláře
     * @return naplněný objekt Customer
     */
    Customer fillCustomerFromForm(HttpServletRequest req);

    /**
     * Naplní objekt Address
     * @param req request z formuláře
     * @return naplněný objekt Address
     */
    Address fillAddressFromForm(HttpServletRequest req);

    /**
     * Naplní objekt ContactInfo
     * @param req request z formuláře
     * @return naplněný objekt ContactInfo
     */
    ContactInfo fillContactInfoFromForm(HttpServletRequest req);

    /**
     * Naplní objekt AccountAddress
     * @param req request z formuláře
     * @return naplněný objekt AccountAddress
     */
    AccountAddress fillAccountAddressFromForm(HttpServletRequest req);

    /**
     * Naplní objekt Account
     * @param req request z formuláře
     * @return naplněný objekt Account
     */
    Account fillSourceAccountAddressFromForm(HttpServletRequest req);

    /**
     * Naplní objekt Move
     * @param req request z formuláře
     * @return naplněný objekt Move
     */
    Move fillMoveFromForm(HttpServletRequest req);

    /**
     * Naplní objekt
     * @param req request z formuláře
     * @return naplněný objekt
     */
    Long fillAccountType(HttpServletRequest req);

    /**
     * Naplní objekt Long
     * @param req request z formuláře
     * @return naplněný objekt Long
     */
    Long fillRBI(HttpServletRequest req);

    /**
     * Naplní objekt Currency
     * @param req request z formuláře
     * @return naplněný objekt Currency
     */
    Currency fillCurrency(HttpServletRequest req);

    /**
     * Naplní objekt Pages
     * @param req request z formuláře
     * @return naplněný objekt Pages
     */
    Pages fillPages(HttpServletRequest req);

}
