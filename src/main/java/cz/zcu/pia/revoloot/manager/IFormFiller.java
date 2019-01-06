package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.Address;
import cz.zcu.pia.revoloot.entities.ContactInfo;
import cz.zcu.pia.revoloot.entities.Customer;

import javax.servlet.http.HttpServletRequest;

public interface IFormFiller {

    Customer fillCustomerFromForm(HttpServletRequest req);
    Address fillAddressFromForm(HttpServletRequest req);
    ContactInfo fillContactInfoFromForm(HttpServletRequest req);
}
