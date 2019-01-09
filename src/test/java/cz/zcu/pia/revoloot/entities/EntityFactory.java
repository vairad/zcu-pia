package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;

import java.util.Date;

public class EntityFactory {

    static IEncoder encoder = new PasswordHashEncoder();

    public static Address createAddress() {
        Address address = new Address();
        address.setState(State.CZ);
        address.setPostalCode(31200);
        address.setCity("Městouš");
        address.setStreet("Uliceuž");
        address.setHouseNo("64E");
        return address;
    }

    public static ContactInfo createContactInfo() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress(createAddress());
        contactInfo.setPhone(720556144);
        contactInfo.setEmail("mail@vut.cz");
        return contactInfo;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setContactInfo(createContactInfo());
        customer.setPersonIDSmart("9311012138");
        customer.setCardID("sad5684");
        customer.setBirthDate(new Date());
        customer.setGender(Gender.MALE);
        customer.setName("Jmeníčko");
        customer.setSurname("Příjméníčko");
        customer.setPassword(encoder.encode("pass"));
        customer.setLogin("login");
        return customer;
    }

    public static Customer createCustomer(String login) {
        Customer c = createCustomer();
        c.setLogin(login);
        return c;
    }

    public static AccountAddress createAccountInfo() {
        AccountAddress accountInfo = new AccountAddress();
        accountInfo.setNumber(222L);
        accountInfo.setBankCode(3666);
        return accountInfo;
    }

    public static Move createMove() {
        Move m = new Move();
        m.setAmount(500.00);
        m.setConstantSymbol(666);
        m.setDestination(createAccountInfo());
        m.setSource(createAccountInfo());
        m.setMessage("Zpráva příjemci");
        m.setNote("Moje tajná poznámka");
        m.setSubmissionDate(new Date());
        m.setCurrency(Currency.CZK);
        return m;
    }

    public static ExchangeRate createExchangeRate(Currency from, Currency to, double rate){
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setFromCur(from);
        exchangeRate.setToCur(to);
        exchangeRate.setRate(rate);
        return exchangeRate;
    }
}
