package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.User;

/**
 * Slušba pro odesílání prefabrikovaných emailových oznámení
 *
 * @author Radek VAIS
 */
public interface IMailSender {

    /**
     * Odešle zprávu o vytvoření účtu s informací o přihlašovacích údajích
     *
     * @param to       příjemce
     * @param login    login nového uživatele
     * @param password heslo nového
     */
    void sendCreationMessage(String to, String login, String password);

    /**
     * Odešle zprávu o změně účtu
     *
     * @param to   příjemce
     * @param user autor tzměny
     */
    void sendUpdateMessage(String to, User user);

    /**
     * Odešle zprávu o úspěšném dokončení pohybu na účtech
     *
     * @param to   příjemce
     * @param move zpracovaný pohyb
     */
    void sendMoveSuccess(String to, Move move);

    /**
     * Odešle zprávu o zrušeném pohybu bankou
     *
     * @param to   příjemce
     * @param move zrušený pohyb
     */
    void sendMoveError(String to, Move move);

    /**
     * Odešle zprávu o zadání platby
     *
     * @param to   příjemce
     * @param move připravený pohyb
     */
    void sendMovePrepares(String to, Move move);

    /**
     * Odešle zprávu o příjmu na účet
     *
     * @param to   příjemce
     * @param move pohyb příjmu
     */
    void sendReceiveMoney(String to, Move move);

    /**
     * Odešle zprávu o odstranění zákazíka ze systému
     *
     * @param to     příjemce
     * @param banker autor odstranění
     */
    void sendRemoveMessage(String to, User banker);
}
