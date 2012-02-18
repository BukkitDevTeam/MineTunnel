package server;

import java.net.InetSocketAddress;
import minetunnel.MineTunnel;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Server {

    private final ServerBootstrap server = new ServerBootstrap();

    public Channel start(String host, int port) throws Exception {
        server.setFactory(new NioServerSocketChannelFactory(MineTunnel.executor, MineTunnel.executor));
        server.setPipelineFactory(new ServerPipelineFactory());
        return server.bind(new InetSocketAddress(host, port));
    }
}
