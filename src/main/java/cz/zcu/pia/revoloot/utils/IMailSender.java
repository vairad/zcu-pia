package cz.zcu.pia.revoloot.utils;

import cz.zcu.pia.revoloot.entities.Move;
import cz.zcu.pia.revoloot.entities.User;

public interface IMailSender {
    void sendCreationMessage(String to, String login, String password);

    void sendUpdateMessage(String to, User user);

    void sendNewPassword(String to, String password);

    void sendMoveSuccess(String to, Move move);

    void sendMoveError(String to, Move move);

    void sendMovePrepares(String to, Move move);

    void sendReceiveMoney(String to, Move move);

    void sendRemoveMessage(String email, User banker);
}
