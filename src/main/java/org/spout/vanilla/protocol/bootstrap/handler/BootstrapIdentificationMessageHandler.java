package org.spout.vanilla.protocol.bootstrap.handler;

import minetunnel.Player;
import minetunnel.Session;
import org.spout.api.protocol.MessageHandler;
import message.IdentificationMessage;

public class BootstrapIdentificationMessageHandler extends MessageHandler<IdentificationMessage> {

    @Override
    public void handle(Session session, Player player, IdentificationMessage message) {
        // Event event = new PlayerConnectEvent(session, message.getName());
        // session.getGame().getEventManager().callEvent(event);
    }
}
