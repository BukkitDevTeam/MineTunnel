package client;

import java.net.ConnectException;
import minetunnel.Session;
import org.jboss.netty.channel.*;
import protocol.Message;

public class ClientHandler extends SimpleChannelUpstreamHandler {

    private final Session session;

    public ClientHandler(Session session) {
        this.session = session;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        Throwable t = e.getCause();
        if (t instanceof ConnectException) {
            System.out.println("Error: Could not connect!");
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        session.send((Message) e.getMessage());
    }
}
