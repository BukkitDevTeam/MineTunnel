package handler;

import message.RespawnMessage;
import minetunnel.Player;
import minetunnel.Session;
import protocol.MessageHandler;

public class RespawnMessageHandler extends MessageHandler<RespawnMessage> {

    @Override
    public void handle(Session session, Player player, RespawnMessage message) {
    }
}
