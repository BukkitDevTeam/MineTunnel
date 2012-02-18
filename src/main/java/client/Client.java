package client;

import java.net.InetSocketAddress;
import minetunnel.MineTunnel;
import minetunnel.Session;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class Client {

    private final ClientBootstrap client = new ClientBootstrap();
    private final FutureListener future;
    private final ClientPipelineFactory pipeline;

    public Client(Session session) {
        future = new FutureListener(session);
        pipeline = new ClientPipelineFactory(session);
    }

    public void start(String host, int port) {
        client.setFactory(new NioClientSocketChannelFactory(MineTunnel.executor, MineTunnel.executor));
        client.setPipelineFactory(pipeline);
        client.connect(new InetSocketAddress(host, port)).addListener(future);
    }
}
