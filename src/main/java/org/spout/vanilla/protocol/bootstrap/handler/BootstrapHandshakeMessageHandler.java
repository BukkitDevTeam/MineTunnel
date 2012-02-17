package org.spout.vanilla.protocol.bootstrap.handler;

import com.md_5.minetunnel.Player;
import com.md_5.minetunnel.Session;
import com.md_5.minetunnel.SessionState;
import org.spout.api.protocol.MessageHandler;
import org.spout.vanilla.protocol.msg.HandshakeMessage;

public class BootstrapHandshakeMessageHandler extends MessageHandler<HandshakeMessage> {

    @Override
    public void handle(Session session, Player player, HandshakeMessage message) {
        SessionState state = session.getState();
        if (state == SessionState.EXCHANGE_HANDSHAKE) {
            session.setState(SessionState.EXCHANGE_IDENTIFICATION);
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
