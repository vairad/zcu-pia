package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;

import javax.servlet.http.HttpServletRequest;

public interface IFormFiller {

    Customer fillCustomerFromForm(HttpServletRequest req);
    Address fillAddressFromForm(HttpServletRequest req);
    ContactInfo fillContactInfoFromForm(HttpServletRequest req);
    AccountAddress fillAccountAddressFromForm(HttpServletRequest req);

    AccountAddress fillSourceAccountAddressFromForm(HttpServletRequest req);

    Move fillMoveFromForm(HttpServletRequest request);
}
