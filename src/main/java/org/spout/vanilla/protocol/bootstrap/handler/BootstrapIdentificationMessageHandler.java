package org.spout.vanilla.protocol.bootstrap.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import com.md_5.minetunnel.Session;
import org.spout.vanilla.protocol.msg.IdentificationMessage;

public class BootstrapIdentificationMessageHandler extends MessageHandler<IdentificationMessage> {

    @Override
    public void handle(Session session, Player player, IdentificationMessage message) {
        // Event event = new PlayerConnectEvent(session, message.getName());
        // session.getGame().getEventManager().callEvent(event);
    }
}
