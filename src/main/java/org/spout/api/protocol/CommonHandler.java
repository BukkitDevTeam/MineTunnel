package org.spout.api.protocol;

import org.jboss.netty.channel.*;
import org.spout.server.net.SpoutSession;
import org.spout.vanilla.protocol.bootstrap.VanillaBootstrapProtocol;

public class CommonHandler extends SimpleChannelUpstreamHandler {

    private volatile Session session = null;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session s = new SpoutSession(c, new VanillaBootstrapProtocol());

        //server.getSessionRegistry().add(session);
        ctx.setAttachment(s);
        this.session = s;

        System.out.println("Channel connected: " + c + ".");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel c = e.getChannel();
        Session session = (Session) ctx.getAttachment();
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
        if (session != null) {
            session.setProtocol(protocol);
        } else {
            throw new IllegalStateException("The protocol cannot be set before the channel is associated with a session");
        }
    }
}
