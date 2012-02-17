package org.spout.vanilla.protocol.bootstrap.handler;

import com.md_5.minetunnel.MineTunnel;
import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.vanilla.protocol.msg.KickMessage;
import org.spout.vanilla.protocol.msg.ServerListPingMessage;

public class BootstrapPingMessageHandler extends MessageHandler<ServerListPingMessage> {

    @Override
    public void handle(Session session, Player player, ServerListPingMessage message) {
        System.out.println("Server list ping event");
        session.send(new KickMessage(MineTunnel.motd + "\u00A7" + MineTunnel.getOnlinePlayers() + "\u00A7" + MineTunnel.getMaxPlayers() + "\u00A7"));
    }
}
