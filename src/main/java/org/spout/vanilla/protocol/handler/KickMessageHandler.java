package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import com.md_5.minetunnel.Session;
import org.spout.api.protocol.MessageHandler;
import org.spout.vanilla.protocol.msg.KickMessage;

public final class KickMessageHandler extends MessageHandler<KickMessage> {

    @Override
    public void handle(Session session, Player player, KickMessage message) {
        super.handle(session, player, message);
    }
}
