package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.dao.ICustomerDAO;
import cz.zcu.pia.revoloot.dao.IExchangeDAO;
import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.dao.db.AccountDAO;
import cz.zcu.pia.revoloot.dao.db.CustomerDAO;
import cz.zcu.pia.revoloot.dao.db.ExchangeDAO;
import cz.zcu.pia.revoloot.dao.db.MoveDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.entities.exceptions.ExchangeRateDoesNotExist;
import cz.zcu.pia.revoloot.utils.BasicBankNumerGenerator;
import cz.zcu.pia.revoloot.utils.BasicValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static cz.zcu.pia.revoloot.entities.EntityFactory.createAccountInfo;
import static cz.zcu.pia.revoloot.entities.EntityFactory.createCustomer;
import static cz.zcu.pia.revoloot.entities.EntityFactory.createExchangeRate;
import static junit.framework.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MoveManagerTest extends ManagerBaseTest {

    MoveManager moveManager;

    IMoveDAO moveDAO;
    IAccountDAO accountDAO;
    IExchangeDAO exchangeDAO;

    Account one;
    Account two;

    @BeforeEach
    void setUp() {
        setUpConnection();

        moveDAO = new MoveDAO(em);
        accountDAO = new AccountDAO(em);
        exchangeDAO = new ExchangeDAO(em);

        moveManager = new MoveManager(
                moveDAO,
                new BasicValidator(),
                accountDAO,
                exchangeDAO,
                new BasicBankNumerGenerator()
        );

        ICustomerDAO customerDAO = new CustomerDAO(em);
        Customer customer = createCustomer();
        customerDAO.save(customer);

        AccountAddress accountAddress = createAccountInfo();
        accountAddress.setNumber(222L);

        one = new Account();
        one.setCustomer(customer);
        one.setCurrency(Currency.CZK);
        one.setAccountInfo(accountAddress);
        one.setTrueAmount(5000.0);
        one.setAmount(5000.0);
        accountDAO.save(one);

        accountAddress = createAccountInfo();
        accountAddress.setNumber(333L);

        two = new Account();
        two.setCustomer(customer);
        two.setCurrency(Currency.CZK);
        two.setAccountInfo(accountAddress);
        two.setTrueAmount(5000.0);
        two.setAmount(5000.0);
        accountDAO.save(two);


        exchangeDAO.save(createExchangeRate(Currency.CZK, Currency.GBP, 0.5));
        exchangeDAO.save(createExchangeRate(Currency.GBP, Currency.CZK, 2.0));
    }

    @AfterEach
    void tearDown() {
        endConnection();
    }

    @Test
    void sendMoney() {
    }


    @Test
    void lookUpExchangeRateSame() throws ExchangeRateDoesNotExist {
        double rate = moveManager.lookUpExchangeRate(Currency.CZK, Currency.CZK);
        assertEquals(1.0, rate, 0.000001);
    }

    @Test
    void lookUpExchangeRateFrom() throws ExchangeRateDoesNotExist {
        double rate = moveManager.lookUpExchangeRate(Currency.CZK, Currency.GBP);
        assertEquals(0.5, rate, 0.000001);
    }

    @Test
    void lookUpExchangeRateTo() throws ExchangeRateDoesNotExist {
        double rate = moveManager.lookUpExchangeRate(Currency.GBP, Currency.CZK);
        assertEquals(2.0, rate, 0.000001);
    }


    @Test
    void tryProcessSend() throws ExchangeRateDoesNotExist, NotEnoughMoneyException {
        Move move = new Move();
        move.setCurrency(Currency.CZK);
        move.setAmount(500.0);
        move.setOwner(one);
        move.setSubmissionDate(new Date());
        move.setIncome(false);
        AccountAddress destination = new AccountAddress();
        destination.setNumber(333L);
        destination.setBankCode(3666);

        move.setDestination(destination);
        moveDAO.save(move);

        moveManager.tryProcessSend(one, move);

        assertEquals(5000.0, one.getTrueAmount(), 0.00000001);
        assertEquals(4500.0, one.getAmount(), 0.00000001);
        assertEquals(5000.0, two.getTrueAmount(), 0.00000001);
        assertEquals(5000.0, two.getAmount(), 0.00000001);

        accountDAO.save(one);
        accountDAO.save(two);
    }

    @Test
    void tryProcessReceive() throws ExchangeRateDoesNotExist {
        Move move = new Move();
        move.setCurrency(Currency.CZK);
        move.setAmount(500.0);
        move.setOwner(two);
        move.setSubmissionDate(new Date());
        move.setIncome(true);
        AccountAddress destination = new AccountAddress();
        destination.setNumber(333L);
        destination.setBankCode(3666);

        move.setDestination(destination);
        moveDAO.save(move);

        moveManager.tryProcessReceive(two, move);

        assertEquals(5500.0, two.getTrueAmount(), 0.00000001);
        assertEquals(5500.0, two.getAmount(), 0.00000001);
        assertNotEquals(5000.0, two.getTrueAmount());
        assertNotEquals(5000.0, two.getAmount());

        accountDAO.save(one);
        accountDAO.save(two);
    }

    @Test
    void processMove() throws ExchangeRateDoesNotExist {
        Move move = new Move();
        move.setCurrency(Currency.CZK);
        move.setAmount(500.0);
        move.setOwner(one);
        move.setSubmissionDate(new Date());
        move.setIncome(false);
        AccountAddress destination = new AccountAddress();
        destination.setNumber(333L);
        destination.setBankCode(3666);

        move.setDestination(destination);
        moveDAO.save(move);

        moveManager.processMove(one, move);

        assertEquals(4500.0, one.getTrueAmount(), 0.00000001);
        assertEquals(5000.0, one.getAmount(), 0.00000001);
        assertEquals(5500.0, two.getTrueAmount(), 0.00000001);
        assertEquals(5500.0, two.getAmount(), 0.00000001);

        assertEquals(500.0, move.getAmount(), 0.00000001);
        assertFalse(move.isIncome());
    }

    @Test
    void passMoneyToReceiver() throws ExchangeRateDoesNotExist {
        Move move = new Move();
        move.setCurrency(Currency.CZK);
        move.setAmount(500.0);
        move.setOwner(one);
        move.setSubmissionDate(new Date());
        move.setIncome(false);
        AccountAddress destination = new AccountAddress();
        destination.setNumber(333L);
        destination.setBankCode(3666);

        move.setDestination(destination);
        moveDAO.save(move);

        moveManager.passMoneyToReceiver(move);

        assertEquals(5000.0, one.getTrueAmount(), 0.00000001);
        assertEquals(5000.0, one.getAmount(), 0.00000001);
        assertEquals(5500.0, two.getTrueAmount(), 0.00000001);
        assertEquals(5500.0, two.getAmount(), 0.00000001);

        assertEquals(500.0, move.getAmount(), 0.00000001);
        assertFalse(move.isIncome());

        moveDAO.save(move);
        accountDAO.save(one);
        accountDAO.save(two);
    }
}