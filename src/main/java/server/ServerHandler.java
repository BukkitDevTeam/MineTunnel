package server;

import client.Client;
import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import protocol.Message;

public class ServerHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Session session = new Session(e.getChannel(), new Client());
        MineTunnel.addSession(session);
        ctx.setAttachment(session);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        MineTunnel.removeSession((Session) ctx.getAttachment());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ((Session) ctx.getAttachment()).messageReceived((Message) e.getMessage());
    }
}
