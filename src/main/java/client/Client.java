package client;

import java.net.InetSocketAddress;
import minetunnel.MineTunnel;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import protocol.PipelineFactory;

public class Client {

    private final ClientBootstrap client = new ClientBootstrap();

    public void start(String host, int port) throws Exception {
        client.setFactory(new NioClientSocketChannelFactory(MineTunnel.executor, MineTunnel.executor));
        client.setPipelineFactory(new PipelineFactory());
        client.connect(new InetSocketAddress(host, port));
    }
}
