package handler;

import minetunnel.Player;
import minetunnel.Session;
import protocol.MessageHandler;
import message.RespawnMessage;

public class RespawnMessageHandler extends MessageHandler<RespawnMessage> {

    @Override
    public void handle(Session session, Player player, RespawnMessage message) {
        //player.setHealth(20);
        //player.teleport(player.getWorld().getSpawnLocation());
    }
}
