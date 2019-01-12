package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.dao.IExchangeDAO;
import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.dao.ITemplateDAO;
import cz.zcu.pia.revoloot.entities.*;
import cz.zcu.pia.revoloot.entities.exceptions.ExchangeRateDoesNotExist;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;
import cz.zcu.pia.revoloot.utils.IBankNumbers;
import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class MoveManager implements IMoveManager {

    private Logger logger = LoggerFactory.getLogger(MoveManager.class.getName());

    private final IValidator validator;

    private final IMoveDAO moveDAO;
    private final IAccountDAO accountDAO;
    private final IExchangeDAO exchangeDAO;
    private final IBankNumbers bankNumbers;
    private final ITemplateDAO templateDAO;

    @Autowired
    public MoveManager(IMoveDAO moveDAO, IValidator validator, IAccountDAO accountDAO, IExchangeDAO exchangeDAO, IBankNumbers bankNumbers, ITemplateDAO templateDAO) {
        this.moveDAO = moveDAO;
        this.validator = validator;
        this.accountDAO = accountDAO;
        this.exchangeDAO = exchangeDAO;
        this.bankNumbers = bankNumbers;
        this.templateDAO = templateDAO;
    }

//region IMoveManager

    /**
     * Ověří, že všechny předpoklady pro uskutečnění převodu jsou naplněny
     *
     * @param move validovaný objekt pohybu
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void sendMoney(boolean save, Move move, long customerID) throws MoveValidationException {
        move.setIncome(false);

        Set<String> errors = move.validate(validator);
        if (!save) {
            errors.add(FormConfig.TURING);
        }
        if (errors.isEmpty()) {
            Account ownerAccount = verifyMoveOwner(move, customerID);
            if (ownerAccount == null) {
                errors.add(FormConfig.MY_ACCOUNT);
                throw new MoveValidationException(errors);
            }
            move.setOwner(ownerAccount);

            try {
                tryProcessSend(ownerAccount, move);
                accountDAO.save(ownerAccount);
                moveDAO.save(move);
                return;
            } catch (NotEnoughMoneyException e) {
                errors.add(FormConfig.AMOUNT);
            } catch (ExchangeRateDoesNotExist e) {
                errors.add(FormConfig.CURRENCY);
            }

        }
        throw new MoveValidationException(errors);
    }

    /**
     * Metoda uloží zvalidovaný pohyb jako šablonu pro další použití.
     *
     * @param move pohyb k uložení
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void addTemplate(boolean save, String templateName, Move move, Customer user) throws MoveValidationException {
        Set<String> errors = move.validate(validator);
        if (!save) {
            errors.add(FormConfig.TURING);
        }
        if (validator.isEmptyField(templateName)) {
            errors.add(FormConfig.TEMPLATE_NAME);
        }
        if (errors.isEmpty()) {
            Account account = verifyMoveOwner(move, user.getId());
            if (account == null) {
                errors.add(FormConfig.MY_ACCOUNT);
                throw new MoveValidationException(errors);
            }
            move.setOwner(account);

            Template template = new Template();
            template.setName(templateName);
            template.setOwner(user);
            template.setMove(move);
            try {
                templateDAO.save(template);
            } catch (PersistenceException ex) {
                errors.add(FormConfig.TEMPLATE_NAME);
                throw new MoveValidationException(errors);
            }
            return;
        }
        throw new MoveValidationException(errors);
    }

    @Override
    public Template loadTemplate(Long templateID, Long id) {
        if (templateID == null || id == null) {
            return null;
        }
        Template template = templateDAO.findOne(templateID);
        if (template == null) {
            return null;
        }
        if (!template.getOwner().getId().equals(id)) {
            return null;
        }
        return template;
    }

    @Override
    public void removeTemplate(Long templateId, Long id) {
        if (templateId == null || id == null) {
            return;
        }
        Template template = templateDAO.findOne(templateId);
        if (template == null) {
            return;
        }
        if (!template.getOwner().getId().equals(id)) {
            return;
        }
        templateDAO.remove(template);
    }

    @Override
    public void processMoves(int limit) {
        List<Move> moves = moveDAO.getMovesToProcess(limit);
        for (Move move : moves) {
            Account ownerAcc = move.getOwner();
            try {
                processMove(ownerAcc, move);
                moveDAO.save(move);
                accountDAO.save(ownerAcc);
            } catch (ExchangeRateDoesNotExist exchangeRateDoesNotExist) {
                logger.warn("ExchangeRateDoesNotExist move was not processed", exchangeRateDoesNotExist);
            }
        }
    }

    @Override
    public List<Template> getTemplatesByUser(Long id) {
        if (id == null) {
            return null;
        }
        return templateDAO.getAllTemplatesByUser(id);
    }
//endregion IMoveManager

//region hidden (private) functions ... public for tests


    private Account verifyMoveOwner(Move move, long customerID) {
        Long accNo = move.getOwner().getAccountInfo().getNumber();
        Account ownerAccount = accountDAO.checkAccount(customerID, accNo);
        return ownerAccount;
    }

    /**
     * Metoda provede vyhodnocení kurzu.
     * Pokud jsou měny stejné, vrací 1.0 jinak provede dotaz do DB
     *
     * @param from zdrojová měna
     * @param to   cílové měna
     * @return převodní kurz
     */
    public double lookUpExchangeRate(Currency from, Currency to) throws ExchangeRateDoesNotExist {
        double exchangeRate;
        if (from == to) {
            exchangeRate = 1.0;
        } else {
            exchangeRate = exchangeDAO.getExchchangeRate(from, to);
        }
        return exchangeRate;
    }

    /**
     * Meotda provede blokaci ppčástky na účtu v případě před zadáním pohybu do databáze.
     * Provede kontrolu zůstatku, jeho úpravu a případný převod
     *
     * @param ownerAccount účet na kterém dochází k blokaci
     * @param move         pohyb k provedení
     * @throws IllegalArgumentException v případě, že se účet neshoduje s vlastníkem pohybu
     */
    public void tryProcessSend(Account ownerAccount, Move move) throws NotEnoughMoneyException, ExchangeRateDoesNotExist {
        if (!ownerAccount.equals(move.getOwner())) {
            throw new IllegalArgumentException();
        }
        double exchangeRate = lookUpExchangeRate(ownerAccount.getCurrency(), move.getCurrency());

        // přepočet pohybu pro blokaci na účtě
        double movedAmount = move.getAmount() / exchangeRate;
        movedAmount = Math.round(movedAmount * 100.0) / 100.0;

        //kontrola prostředků
        if (ownerAccount.getAmount() < movedAmount) {
            throw new NotEnoughMoneyException();
        }

        //blokace prostředků
        ownerAccount.setAmount(ownerAccount.getAmount() - movedAmount);
    }

    /**
     * Meotda pokusí připsat peníze na cílový účet
     * Provede kontrolu zůstatku, jeho úpravu a případný převod
     * <p>
     * Meotda očekává, že cílový a účet je vlastníkem pohybu a pohyb je příjem jinak vyhodí výjimku
     *
     * @param targetAccount cílový účet pohybu
     * @param move          pohyb
     * @throws IllegalArgumentException v případě, že target account není vlastník pohybu nebo pohyb není příjem
     */
    public void tryProcessReceive(Account targetAccount, Move move) throws ExchangeRateDoesNotExist {
        if (!targetAccount.equals(move.getOwner()) || !move.isIncome()) {
            throw new IllegalArgumentException();
        }
        double exchangeRate = lookUpExchangeRate(move.getCurrency(), targetAccount.getCurrency());

        // přepočet pohybu pro blokaci na účtě
        double movedAmount = move.getAmount() * exchangeRate;
        movedAmount = Math.round(movedAmount * 100.0) / 100.0;

        if (exchangeRate != 1.0) { //jde o jinou měnu uprav pohyb
            move.setBankNote("Příjem cizí měny: " + move.getAmount() + " " + move.getCurrency());
            move.setAmount(movedAmount);
            move.setCurrency(targetAccount.getCurrency());
        }

        //navyš hodnoty na účtu
        targetAccount.setTrueAmount(targetAccount.getTrueAmount() + movedAmount);
        targetAccount.setAmount(targetAccount.getAmount() + movedAmount);

        move.setProcessed(true);
        move.setTransferDate(new Date());
    }

    /**
     * Metoda zpracuje příkaz odchozí platby.
     * <p>
     * Provede přepočet dle aktuálního kurzu.
     * Zkontroluje prostředky. - jinak zruší příkaz
     * Iniciuje předání příjemci
     * V případě převodu přenese informace do poznámky
     *
     * @param ownerAcc účet odesílatele
     * @param move     pohyb ke zpracování
     * @throws ExchangeRateDoesNotExist v případě, že nebyl nalezen kurz převodu
     */
    public void processMove(Account ownerAcc, Move move) throws ExchangeRateDoesNotExist {
        double exchangeRate = lookUpExchangeRate(ownerAcc.getCurrency(), move.getCurrency());

        // přepočet pohybu pro blokaci na účtě
        double movedAmount = move.getAmount() / exchangeRate;
        movedAmount = Math.round(movedAmount * 100.0) / 100.0;

        //kontrola prostředků
        if (ownerAcc.getTrueAmount() < movedAmount) {

            //označení zrušení
            move.setBankNote("Příkaz převodu " + move.getAmount() + " " + move.getCurrency()
                    + " zrušen pro nedostatek financí (" + ownerAcc.getTrueAmount() + " " + ownerAcc.getCurrency() + ").");

            //označení vynulování příkazu
            move.setCurrency(ownerAcc.getCurrency());
            move.setAmount(0.0);

            //zrušení blokace peněz... příkaz zrušen
            ownerAcc.setAmount(ownerAcc.getAmount() + movedAmount);
        } else {
            //odešli peníze příjemci
            passMoneyToReceiver(move);

            // příkaz v jiné měně
            if (exchangeRate != 1.0) {
                move.setBankNote("Příkaz odeslání cizí měny: " + move.getAmount() + " " + move.getCurrency() + ".");
                move.setCurrency(ownerAcc.getCurrency());
                move.setAmount(movedAmount);
            }

            //zaúčtování prostředků prostředků
            ownerAcc.setTrueAmount(ownerAcc.getTrueAmount() - movedAmount);

        }
        move.setTransferDate(new Date());
        move.setProcessed(true);
    }

    /**
     * Metoda iniciuje předání peněz na cílový účet
     *
     * @param move
     * @throws ExchangeRateDoesNotExist
     */
    public void passMoneyToReceiver(Move move) throws ExchangeRateDoesNotExist {
        // adresa v naší bance
        if (move.getDestination().getBankCode().equals(bankNumbers.getBankCode())) {
            Move newMove = new Move(move);
            newMove.setIncome(true);
            Account acc = accountDAO.findByAccountNumber(newMove.getDestination().getNumber());
            if (acc != null) {
                newMove.setOwner(acc);
                tryProcessReceive(acc, newMove);
                moveDAO.save(newMove);
                accountDAO.save(acc);
            } else {
                // return money if account does not exist
                Account owner = move.getOwner();
                double exchangeRate = lookUpExchangeRate(owner.getCurrency(), move.getCurrency());

                // přepočet pohybu pro blokaci na účtě
                double movedAmount = move.getAmount() / exchangeRate;
                movedAmount = Math.round(movedAmount * 100.0) / 100.0;
                //označení zrušení
                move.setBankNote("Příkaz převodu " + move.getAmount() + " " + move.getCurrency()
                        + " zrušen pro neexistenci cílového účtu " + move.getDestination() + ".");

                //označení vynulování příkazu
                move.setCurrency(owner.getCurrency());
                move.setAmount(0.0);

                //zrušení blokace peněz... příkaz zrušen
                move.getOwner().setAmount(owner.getAmount() + movedAmount);
                move.setProcessed(true);
                accountDAO.save(owner);
            }
        }
    }
}
