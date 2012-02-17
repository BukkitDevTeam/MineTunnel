package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.vanilla.protocol.msg.RespawnMessage;

public class RespawnMessageHandler extends MessageHandler<RespawnMessage> {

    @Override
    public void handle(Session session, Player player, RespawnMessage message) {
        //player.setHealth(20);
        //player.teleport(player.getWorld().getSpawnLocation());
    }
}
