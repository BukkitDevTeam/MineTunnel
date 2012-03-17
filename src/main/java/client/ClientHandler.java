package client;

import java.net.ConnectException;
import minetunnel.Session;
import org.jboss.netty.channel.*;

public class ClientHandler extends SimpleChannelUpstreamHandler {

    private final Session session;

    public ClientHandler(Session session) {
        this.session = session;
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        Throwable t = e.getCause();
        if (t instanceof ConnectException) {
            System.err.println("Error: Could not connect!");
        } else {
            t.printStackTrace();
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        session.send(e.getMessage());
    }
}
