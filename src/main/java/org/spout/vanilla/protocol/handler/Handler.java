package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import com.md_5.minetunnel.Session;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;

public class Handler extends MessageHandler<Message> {

    @Override
    public void handle(Session session, Player player, Message message) {
        super.handle(session, player, message);
    }
}
