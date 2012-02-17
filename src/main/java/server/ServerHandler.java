package server;

import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.*;
import protocol.Message;
import protocol.VanillaProtocol;

public class ServerHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session session = new Session(c, new VanillaProtocol());
        MineTunnel.getSessions().put(session, false);
        ctx.setAttachment(session);
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
}
