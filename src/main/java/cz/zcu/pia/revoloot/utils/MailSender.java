package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class MailSender implements IMailSender {
    private Logger logger = LoggerFactory.getLogger(MailSender.class.getName());

    /**
     * Formátovač datumů
     */
    private IDateFormatter formatter;

    /**
     * Odesílač mailů
     */
    private final JavaMailSender mailSender;


    @Autowired
    public MailSender(IDateFormatter formatter, JavaMailSender mailSender) {
        this.formatter = formatter;
        this.mailSender = mailSender;
    }

    /**
     * Medoda zajístí filánlní odeslání zprávy.
     * Případné chyby zaznamená do aplikačního logu.
     *
     * @param message zpšáva k odeslání
     */
    private void sendMail(SimpleMailMessage message) {
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            logger.warn("Error sending email", ex);
        }
    }

    /**
     * Metoda připraví základní tělo zprávy
     *
     * @param to      email příjemce
     * @param subject předmět zprávy
     * @return připravená zpráva
     */
    private SimpleMailMessage prepareMessage(String to, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Revolution Loot - " + subject);
        return message;
    }

    /**
     * Odešle informaci o vytvoření účtu včetně přihlašovacích údajů
     *
     * @param to       příjemce
     * @param login    login uživatele
     * @param password heslo uživatele
     */
    @Override
    @Async
    public void sendCreationMessage(String to, String login, String password) {
        SimpleMailMessage message = prepareMessage(to, "Založení účtu");
        message.setText("Dobrý den,\nprávě jsme vytvořili váš účet pro bankovní systém Revolution Loot.\n Vaše přihlašovací údaje jsou následující:\n " +
                "login: " + login + "\n" +
                "heslo:" + password + "\n" +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle informaci o úpravě účtu.
     *
     * @param to   příjemce
     * @param user autor úpravy
     */
    @Override
    @Async
    public void sendUpdateMessage(String to, User user) {
        SimpleMailMessage message = prepareMessage(to, "Úprava údajů");
        message.setText("Dobrý den,\nuživatel " + user.getName() + " " + user.getSurname() + " právě upravil Vaše osobní údaje.\n " +
                "Pokud jste to nebyl Vy kontaktujte prosím Vašeho bankovního poradce\n " +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle nové heslo k úživatelskému účtu
     *
     * @param to       příjemce
     * @param password nové heslo
     */
    public void sendNewPassword(String to, String password) {
        SimpleMailMessage message = prepareMessage(to, "Nové heslo");
        message.setText("Dobrý den,\nzasíláme Vám nově vygenerované heslo: " + password +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle oznámení o zaúčtování příkazu k úhradě
     *
     * @param to   příjemce
     * @param move zaúčtovaný pohyb
     */
    @Override
    @Async
    public void sendMoveSuccess(String to, Move move) {
        SimpleMailMessage message = prepareMessage(to, "Převod prostředků");
        message.setText("Dobrý den,\n Vámi zadaný příkaz k úhradě: " + move.getAmount() + " " + move.getCurrency() +
                " ve prospěch účtu " + move.getDestination() + "byl úspěšně zpracován.\n " +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle oznámení o zaítnutí příkazu k úhradě
     *
     * @param to   příjemce
     * @param move pohyb
     */
    @Override
    @Async
    public void sendMoveError(String to, Move move) {
        SimpleMailMessage message = prepareMessage(to, "Příkaz zrušen");
        message.setText("Dobrý den,\n Vámi zadaný příkaz k úhradě: " + move.getAmount() + " " + move.getCurrency() +
                " ve prospěch účtu " + move.getDestination() + "nebyl zpracován z důvodu:\n " + move.getBankNote() +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle oznámení o přijetí příkazu ke zpracování
     *
     * @param to   příjemce
     * @param move pohyb ke zpracování
     */
    @Override
    @Async
    public void sendMovePrepares(String to, Move move) {
        SimpleMailMessage message = prepareMessage(to, "Příkaz zařazen ke zpracování");
        message.setText("Dobrý den,\n Vámi zadaný příkaz k úhradě: " + move.getAmount() + " " + move.getCurrency() +
                " ve prospěch účtu " + move.getDestination() + "je přepraven ke zpracování a bude proveden:\n " +
                formatter.dateTimeFormat(move.getSubmissionDate()) +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle informaci o přijetí částky na účet
     *
     * @param to   příjemce
     * @param move pohyb přijaté částky
     */
    @Override
    @Async
    public void sendReceiveMoney(String to, Move move) {
        SimpleMailMessage message = prepareMessage(to, "Přišly peníze");
        message.setText("Dobrý den,\n na Váš účet " + move.getOwner().getAccountInfo() + " přišly peníze: " + move.getAmount() + " " + move.getCurrency() +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }

    /**
     * Odešle informaci o zrušení účtu
     *
     * @param to   příjemce
     * @param user iniciátor zrušení
     */
    @Override
    @Async
    public void sendRemoveMessage(String to, User user) {
        SimpleMailMessage message = prepareMessage(to, "Úprava údajů");
        message.setText("Dobrý den,\nuživatel " + user.getName() + " " + user.getSurname() + " právě zrušil Váš účet\n " +
                "Pokud jste to nebyl Vy kontaktujte prosím Vašeho bankovního poradce\n " +
                "\nS přáním úspěšného dne\n Tým Revolution Loot");
        sendMail(message);
    }
}
