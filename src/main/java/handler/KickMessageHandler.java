package handler;

import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;
import message.KickMessage;

public final class KickMessageHandler extends MessageHandler<KickMessage> {

    @Override
    public void handle(Session session, Player player, KickMessage message) {
        super.handle(session, player, message);
    }
}
