package org.spout.vanilla.protocol.bootstrap.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.vanilla.protocol.msg.HandshakeMessage;

public class BootstrapHandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, Player player, HandshakeMessage message) {
        Session.State state = session.getState();
        if (state == Session.State.EXCHANGE_HANDSHAKE) {
            session.setState(Session.State.EXCHANGE_IDENTIFICATION);
            // TODO
            //if (session.getServer().getOnlineMode()) {
            //	session.send(new HandshakeMessage(session.getSessionId()));
            //} else {
            session.send(new HandshakeMessage("-"));
            //}
        } else {
            session.disconnect("Handshake already exchanged.");
        }
    }
}
