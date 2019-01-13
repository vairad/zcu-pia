package cz.zcu.pia.revoloot.entities;

import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        try {
            customer.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/11/1993"));
        } catch (ParseException e) {
            customer.setBirthDate(new Date());
        }
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

    public static Product createProduct(){
        Product product = new Product();
        product.setName("Běžný účet");
        product.setShortText("Běžný účet pro každého");
        product.setTerms("<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam."
                + "</p>" +
                "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam."
                + "</p>" +
                "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam."
                + "</p>" +
                "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Sed convallis magna eu sem. Nulla non lectus sed nisl molestie malesuada. Maecenas lorem. Nullam dapibus fermentum ipsum. Etiam commodo dui eget wisi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Etiam quis quam"
                + "</p>"
        );
        return product;
    }

    public static Template createTemplate(){
        Template template = new Template();
        template.setMove(createMove());
        template.setName("Šablona 0");
        return template;
    }
}
