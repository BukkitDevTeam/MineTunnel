package handler;

import message.GetInfoMessage;
import minetunnel.MineTunnel;
import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;

public class GetInfoHandler extends MessageHandler<GetInfoMessage> {

    @Override
    public void handle(Session session, Player player, GetInfoMessage message) {
        session.send(MineTunnel.getPingMessage());
    }
}
