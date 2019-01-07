package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FormFiller implements IFormFiller {

    private Logger logger = LoggerFactory.getLogger(FormFiller.class.getName());

    private Date parseDate(String dateRepresentation) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateRepresentation);
            return date;
        } catch (ParseException | NullPointerException e) {
            logger.warn("date could not be parsed");
        }
        return null;
    }

    private Integer parseInteger(String intRepre) {
        if (intRepre == null || intRepre.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(intRepre);
        } catch (NumberFormatException ex) {
            logger.warn("Wrong number format", ex);
        }
        return null;
    }

    private Long parseLong(String longRepre) {
        if (longRepre == null || longRepre.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(longRepre);
        } catch (NumberFormatException ex) {
            logger.warn("Wrong number format", ex);
        }
        return null;
    }

    @Override
    public Customer fillCustomerFromForm(HttpServletRequest req) {
        logger.info("start");
        Customer customer = new Customer();
        customer.setName(req.getParameter(FormConfig.NAME));
        customer.setSurname(req.getParameter(FormConfig.SURNAME));
        String date = req.getParameter(FormConfig.BIRTH_DATE);
        customer.setBirthDate(parseDate(date));

        customer.setCardID(req.getParameter(FormConfig.CARD_ID));
        customer.setPersonIDSmart(req.getParameter(FormConfig.PERSON_ID));

        String gender = req.getParameter(FormConfig.GENDER);
        try {
            customer.setGender(Gender.fromString(gender));
        } catch (IllegalArgumentException | NullPointerException ex) {
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
        } catch (NumberFormatException e) {
            logger.warn("postal code could not be parsed");
        }

        String state = req.getParameter(FormConfig.STATE);
        try {
            address.setState(State.fromString(state));
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.warn("gender could not be parsed");
        }

        return address;
    }

    @Override
    public ContactInfo fillContactInfoFromForm(HttpServletRequest req) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress(fillAddressFromForm(req));

        if (req.getParameter(FormConfig.EMAIL_1).equals(req.getParameter(FormConfig.EMAIL_2)))
            contactInfo.setEmail(req.getParameter(FormConfig.EMAIL_1));

        return contactInfo;
    }

    @Override
    public AccountAddress fillAccountAddressFromForm(HttpServletRequest req) {
        AccountAddress accountAddress = new AccountAddress();
        accountAddress.setBankCode(parseInteger(req.getParameter(FormConfig.BANK_CODE)));
        accountAddress.setPrepend(parseLong(req.getParameter(FormConfig.PRE_ACC_NUM)));
        accountAddress.setNumber(parseLong(req.getParameter(FormConfig.ACC_NUM)));

        return accountAddress;
    }

    @Override
    public Move fillMoveFromForm(HttpServletRequest req) {
        Move move = new Move();
        move.setDestination(fillAccountAddressFromForm(req));
        move.setAmount(parseLong(req.getParameter(FormConfig.AMOUNT)));
        move.setSubmissionDate(parseDate(req.getParameter(FormConfig.DUE_DATE)));
        move.setVariableSymbol(parseInteger(req.getParameter(FormConfig.VARIABLE_SYMBOL)));
        move.setConstantSymbol(parseInteger(req.getParameter(FormConfig.CONSTANT_SYMBOL)));
        move.setSpecificSymbol(parseInteger(req.getParameter(FormConfig.SPECIFIC_SYMBOL)));

        return move;
    }
}
