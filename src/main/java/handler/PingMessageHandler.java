package handler;

import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;
import message.PingMessage;

public class PingMessageHandler extends MessageHandler<PingMessage> {

    @Override
    public void handle(Session session, Player player, PingMessage message) {
        super.handle(session, player, message);
    }
}
