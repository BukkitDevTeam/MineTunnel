package handler;

import message.HandshakeMessage;
import minetunnel.Session;
import protocol.MessageHandler;

public class HandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, HandshakeMessage message) {
        session.send(new HandshakeMessage("-"));
    }
}
