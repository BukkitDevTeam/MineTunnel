package handler;

import message.KickMessage;
import minetunnel.Session;
import protocol.MessageHandler;

public class KickMessageHandler extends MessageHandler<KickMessage> {

    @Override
    public void handle(Session session, KickMessage message) {
        session.disconnect(message.getReason());
    }
}
