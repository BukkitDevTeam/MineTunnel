package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;

public class Handler extends MessageHandler<Message> {

    @Override
    public void handle(Session session, Player player, Message message) {
        super.handle(session, player, message);
    }
}
