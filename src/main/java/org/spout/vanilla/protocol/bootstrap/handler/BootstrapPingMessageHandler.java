package org.spout.vanilla.protocol.bootstrap.handler;

import minetunnel.MineTunnel;
import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;
import message.KickMessage;
import message.ServerListPingMessage;

public class BootstrapPingMessageHandler extends MessageHandler<ServerListPingMessage> {

    @Override
    public void handle(Session session, Player player, ServerListPingMessage message) {
        System.out.println("Server list ping event");
        session.send(new KickMessage(MineTunnel.motd + "\u00A7" + MineTunnel.getOnlinePlayers() + "\u00A7" + MineTunnel.getMaxPlayers() + "\u00A7"));
    }
}
