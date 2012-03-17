package server;

import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.channel.*;
import protocol.HandlerLookupService;
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
        session.leaveServer();
        MineTunnel.removeSession(session);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        Throwable t = e.getCause();
        t.printStackTrace();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Message message = (Message) e.getMessage();
        if (HandlerLookupService.find(message.getClass()) != null) {
            session.messageReceived(message);
        } else {
            session.sendServer(message);
        }
    }
}
