package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.Player;
import com.md_5.minetunnel.SessionState;
import org.spout.api.protocol.MessageHandler;
import com.md_5.minetunnel.Session;
import org.spout.vanilla.protocol.msg.HandshakeMessage;

public final class HandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, Player player, HandshakeMessage message) {
        SessionState state = session.getState();
        if (state == SessionState.EXCHANGE_HANDSHAKE) {
            session.setState(SessionState.EXCHANGE_IDENTIFICATION);
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
