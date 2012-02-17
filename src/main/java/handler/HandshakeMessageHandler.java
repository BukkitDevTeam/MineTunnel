package handler;

import message.HandshakeMessage;
import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;

public final class HandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, Player player, HandshakeMessage message) {
        session.send(new HandshakeMessage("-"));
    }
}
