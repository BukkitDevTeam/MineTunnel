package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.vanilla.protocol.msg.PingMessage;

public class PingMessageHandler extends MessageHandler<PingMessage> {

    @Override
    public void handle(Session session, Player player, PingMessage message) {
    }
}
