package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FormFiller implements IFormFiller {

    private Logger logger = LoggerFactory.getLogger(FormFiller.class.getName());

    @Override
    public Customer fillCustomerFromForm(HttpServletRequest req) {
        logger.info("start");
        Customer customer = new Customer();
        customer.setName(req.getParameter(FormConfig.NAME));
        customer.setSurname(req.getParameter(FormConfig.SURNAME));
        String date = req.getParameter(FormConfig.BIRTH_DATE);
        try {
            customer.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (ParseException | NullPointerException e) {
           logger.warn("date could not be parsed");
        }

        customer.setCardID(req.getParameter(FormConfig.CARD_ID));
        customer.setPersonIDSmart(req.getParameter(FormConfig.PERSON_ID));

        String gender = req.getParameter(FormConfig.GENDER);
        try {
            customer.setGender(Gender.fromString(gender));
        }catch (IllegalArgumentException | NullPointerException ex){
            logger.warn("gender could not be parsed");
        }

        customer.setContactInfo(fillContactInfoFromForm(req));

        return customer;
    }

    @Override
    public Address fillAddressFromForm(HttpServletRequest req) {
        Address address = new Address();
        address.setHouseNo(req.getParameter(FormConfig.HOUSE_NUMBER));
        address.setStreet(req.getParameter(FormConfig.STREET));
        address.setCity(req.getParameter(FormConfig.CITY));
        try {
            int postalCode = Integer.parseInt(req.getParameter(FormConfig.POSTAL_CODE));
            address.setPostalCode(postalCode);
        }catch (NumberFormatException e){
            logger.warn("postal code could not be parsed");
        }

        String state = req.getParameter(FormConfig.STATE);
        try {
            address.setState(State.fromString(state));
        }catch (IllegalArgumentException | NullPointerException ex){
            logger.warn("gender could not be parsed");
        }

        return address;
    }

    @Override
    public ContactInfo fillContactInfoFromForm(HttpServletRequest req) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress(fillAddressFromForm(req));

        if(req.getParameter(FormConfig.EMAIL_1).equals(req.getParameter(FormConfig.EMAIL_2)))
        contactInfo.setEmail(req.getParameter(FormConfig.EMAIL_1));

        return contactInfo;
    }
}
