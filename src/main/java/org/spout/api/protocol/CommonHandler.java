package org.spout.api.protocol;

import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.*;
import org.spout.vanilla.protocol.bootstrap.VanillaBootstrapProtocol;

public class CommonHandler extends SimpleChannelUpstreamHandler {

    private Session s;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session session = new Session(c, new VanillaBootstrapProtocol());
        MineTunnel.getSessionRegistry().add(session);
        ctx.setAttachment(session);
        s = session;
        System.out.println("Channel connected: " + c + ".");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session session = (Session) ctx.getAttachment();
        MineTunnel.getSessionRegistry().remove(session);
        session.dispose(true);
        System.out.println("Channel disconnected: " + c + ".");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Session session = (Session) ctx.getAttachment();
        session.messageReceived((Message) e.getMessage());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    public void setProtocol(Protocol protocol) {
        if (s != null) {
            s.setProtocol(protocol);
        } else {
            throw new IllegalStateException("The protocol cannot be set before the channel is associated with a session");
        }
    }
}
