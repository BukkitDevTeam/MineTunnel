package client;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import minetunnel.MineTunnel;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import server.ServerPipelineFactory;

public class Client {

    private final ClientBootstrap client = new ClientBootstrap();

    public Channel start(String host, int port) throws Exception {
        client.setFactory(new NioClientSocketChannelFactory(MineTunnel.executor, MineTunnel.executor));
        client.setPipelineFactory(new ServerPipelineFactory());
        client.connect(new InetSocketAddress(host, port)).;
    }
}
