package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.api.protocol.Session.State;
import org.spout.vanilla.protocol.msg.HandshakeMessage;

public final class HandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, Player player, HandshakeMessage message) {
        Session.State state = session.getState();
        if (state == Session.State.EXCHANGE_HANDSHAKE) {
            session.setState(State.EXCHANGE_IDENTIFICATION);
            //if (session.getGame().getOnlineMode()) {
            //	session.send(new HandshakeMessage(session.getSessionId()));
            //} else {
            session.send(new HandshakeMessage("-"));
            //}
        } else {
            session.disconnect("Handshake already exchanged.");
        }
    }
}
