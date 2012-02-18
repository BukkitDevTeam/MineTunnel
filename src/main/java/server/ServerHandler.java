package server;

import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import protocol.Message;

public class ServerHandler extends SimpleChannelUpstreamHandler {

    private Session session;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        session = new Session(e.getChannel());
        MineTunnel.addSession(session);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        MineTunnel.removeSession(session);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        session.sendServer((Message) e.getMessage());
        //session.messageReceived((Message) e.getMessage());
    }
}
