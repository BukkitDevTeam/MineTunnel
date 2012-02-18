package handler;

import message.ServerListPingMessage;
import minetunnel.MineTunnel;
import minetunnel.Session;
import protocol.MessageHandler;

public class ServerListPingMessageHandler extends MessageHandler<ServerListPingMessage> {

    @Override
    public void handle(Session session, ServerListPingMessage message) {
        session.send(MineTunnel.getPingMessage());
    }
}
