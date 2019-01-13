package cz.zcu.pia.revoloot.dao;

import cz.zcu.pia.revoloot.dao.db.*;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.utils.IEncoder;
import cz.zcu.pia.revoloot.utils.IPasswordGenerator;
import cz.zcu.pia.revoloot.utils.PasswordHashEncoder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static cz.zcu.pia.revoloot.entities.EntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DefaultTest extends DaoTest {
    private static ICustomerDAO customerDAO;
    private static IAccountDAO accountDAO;
    private static IMoveDAO moveDAO;
    private static IBankerDAO bankerDAO;
    private static IExchangeDAO exchangeDAO;
    private static IProductDAO productDAO;
    private static ITemplateDAO templateDAO;

    private static IEncoder encoder;
    private static IPasswordGenerator generator;


    static Banker prepareBanker(String login, String image) {
        Address address = new Address();
        address.setCity("Technická");
        address.setHouseNo("8");
        address.setStreet("Plzeň");
        address.setPostalCode(30100);
        address.setState(State.CZ);


        Banker banker = new Banker();
        banker.setBranch(address);
        banker.setLogin(login);
        banker.setPassword(encoder.encode("pass"));
        banker.setName("Michal");
        banker.setSurname("Opravář");
        banker.setPhoto(image);
        banker.setEmail(login + "@revoloot.cz");
        banker.setGender(Gender.MALE);

        return banker;
    }

    // region preprare tests
    @BeforeAll
    static void setUpDependencies() {
        encoder = new PasswordHashEncoder();
        customerDAO = new CustomerDAO(em);
        accountDAO = new AccountDAO(em);
        moveDAO = new MoveDAO(em);
        bankerDAO = new BankerDAO(em);
        exchangeDAO = new ExchangeDAO(em);
        productDAO = new ProductDAO(em);
        templateDAO = new TemplateDAO(em);
    }


    @Test
    void save() {
        Customer customer = EntityFactory.createCustomer();
        customer.setLogin("User0001");
        customer.setPassword(encoder.encode("0001"));
        customer = customerDAO.save(customer);
        assertNotEquals(customer.getId(), 0L, "Id was not returned.");

        customer = EntityFactory.createCustomer();
        customer.setLogin("User0002");
        customer.setPassword(encoder.encode("0002"));
        customer = customerDAO.save(customer);
        assertNotEquals(customer.getId(), 0L, "Id was not returned.");

        customer = EntityFactory.createCustomer();
        customer.setLogin("test");
        customer.setPassword(encoder.encode("pass"));
        customer = customerDAO.save(customer);
        assertNotEquals(customer.getId(), 0L, "Id was not returned.");

        Product product = createProduct();
        product.setMarketing(true);

        productDAO.save(product);

        product = createProduct();
        product.setName("Speciání účet");
        productDAO.save(product);

        product = createProduct();
        product.setName("Super účet");
        productDAO.save(product);

        Template t = createTemplate();
        t.setOwner(customer);
        templateDAO.save(t);

        Banker banker = prepareBanker("Admin001", "banker1.png");
        banker.setPassword(encoder.encode("1234"));
        banker = bankerDAO.save(banker);
        assertNotEquals(banker.getId(), 0L, "Id was not returned.");

        Banker banker2 = prepareBanker("bank1", "banker2.png");
        banker2 = bankerDAO.save(banker2);
        assertNotEquals(banker2.getId(), 0L, "Id was not returned.");

        ExchangeRate ex = createExchangeRate(Currency.CZK, Currency.GBP, 0.0351080553);
        exchangeDAO.save(ex);
        ex = createExchangeRate(Currency.GBP, Currency.CZK, 28.4834916);
        exchangeDAO.save(ex);

        ex = createExchangeRate(Currency.CZK, Currency.EUR, 0.0391219169);
        exchangeDAO.save(ex);
        ex = createExchangeRate(Currency.EUR, Currency.CZK, 25.5611197);
        exchangeDAO.save(ex);

    }

}
