package cz.zcu.pia.revoloot.manager;

import cz.zcu.pia.revoloot.dao.IAccountDAO;
import cz.zcu.pia.revoloot.dao.IExchangeDAO;
import cz.zcu.pia.revoloot.dao.IMoveDAO;
import cz.zcu.pia.revoloot.entities.Account;
import cz.zcu.pia.revoloot.entities.Currency;
import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.exceptions.ExchangeRateDoesNotExist;
import cz.zcu.pia.revoloot.entities.exceptions.MoveValidationException;
import cz.zcu.pia.revoloot.utils.IValidator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Service
public class MoveManager implements IMoveManager {

    private final IValidator validator;

    private final IMoveDAO moveDAO;
    private final IAccountDAO accountDAO;
    private final IExchangeDAO exchangeDAO;

    @Autowired
    public MoveManager(IMoveDAO moveDAO, IValidator validator, IAccountDAO accountDAO, IExchangeDAO exchangeDAO) {
        this.moveDAO = moveDAO;
        this.validator = validator;
        this.accountDAO = accountDAO;
        this.exchangeDAO = exchangeDAO;
    }

    /**
     * Ověří, že všechny předpoklady pro uskutečnění převodu jsou naplněny
     *
     * @param move validovaný objekt pohybu
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void sendMoney(Move move, long customerID) throws MoveValidationException {
        move.setIncome(false);

        Set<String> errors = move.validate(validator);
        if (errors.isEmpty()) {
            Long accNo = move.getOwner().getAccountInfo().getNumber();
            Account ownerAccount = accountDAO.checkAccount(customerID, accNo);

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
            }catch (ExchangeRateDoesNotExist e){
                errors.add(FormConfig.CURRENCY);
            }

        }
        throw new MoveValidationException(errors);
    }

    /**
     * Metoda provede vyhodnocení kurzu.
     * Pokud jsou měny stejné, vrací 1.0 jinak provede dotaz do DB
     *
     * @param from zdrojová měna
     * @param to   cílové měna
     * @return převodní kurz
     */
    private double lookUpExchangeRate(Currency from, Currency to) throws ExchangeRateDoesNotExist {
        double exchangeRate;
        if (from == to) {
            exchangeRate = 1.0;
        } else {
            exchangeRate = exchangeDAO.getExchchangeRate(from, to);
        }
        return exchangeRate;
    }

    /**
     * Meotda pokusí odeslat peníze z účtu
     * Provede kontrolu zůstatku, jeho úpravu a případný převod
     *
     * @param ownerAccount
     * @param move
     * @return
     */
    private void tryProcessSend(Account ownerAccount, Move move) throws NotEnoughMoneyException, ExchangeRateDoesNotExist {
        double exchangeRate = lookUpExchangeRate(ownerAccount.getCurrency(), move.getCurrency());

        // přepočet pohybu pro blokaci na účtě
        double movedAmount = move.getAmount() / exchangeRate;

        //kontrola prostředků
        if (ownerAccount.getAmount() < movedAmount){
            throw new NotEnoughMoneyException();
        }

        //blokace prostředků
        ownerAccount.setAmount(ownerAccount.getAmount() - movedAmount);
    }

    /**
     * Metoda uloží zvalidovaný pohyb jako šablonu pro další použití.
     *
     * @param move pohyb k uložení
     * @throws MoveValidationException v případě validační chyby
     */
    @Override
    public void addTemplate(String templateName, Move move) throws MoveValidationException {
        Set<String> errors = move.validate(validator);
        if(validator.isEmptyField(templateName)){
            errors.add(FormConfig.TEMPLATE_NAME);
        }
        if (errors.isEmpty()) {
            //moveDAO.save(move);
            return;
        }
        throw new MoveValidationException(errors);
    }

    @Override
    public void processMoves() {
    }
}
