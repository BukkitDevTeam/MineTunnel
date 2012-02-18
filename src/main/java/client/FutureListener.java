package client;

import minetunnel.Session;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

public class FutureListener implements ChannelFutureListener {

    private final Session session;

    public FutureListener(Session session) {
        this.session = session;
    }

    public void operationComplete(ChannelFuture future) throws Exception {
        session.connected(future.getChannel());
    }
}
