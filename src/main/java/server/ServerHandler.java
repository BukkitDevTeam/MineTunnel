package server;

import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.*;
import protocol.Message;
import protocol.Protocol;
import protocol.VanillaProtocol;

public class ServerHandler extends SimpleChannelUpstreamHandler {

    private Session s;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session session = new Session(c, new VanillaProtocol());
        MineTunnel.getSessions().put(session, false);
        ctx.setAttachment(session);
        s = session;
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Session session = (Session) ctx.getAttachment();
        MineTunnel.getSessions().remove(session);
        session.dispose(true);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Session session = (Session) ctx.getAttachment();
        session.messageReceived((Message) e.getMessage());
    }

    public void setProtocol(Protocol protocol) {
        if (s != null) {
            s.setProtocol(protocol);
        } else {
            throw new IllegalStateException("The protocol cannot be set before the channel is associated with a session");
        }
    }
}
