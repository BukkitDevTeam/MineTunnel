package handler;

import message.GetInfoMessage;
import minetunnel.MineTunnel;
import minetunnel.Session;
import protocol.MessageHandler;

public class GetInfoHandler extends MessageHandler<GetInfoMessage> {

    @Override
    public void handle(Session session, GetInfoMessage message) {
        session.send(MineTunnel.getPingMessage());
    }
}
