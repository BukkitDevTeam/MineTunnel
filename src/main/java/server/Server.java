package server;

import java.net.InetSocketAddress;
import minetunnel.MineTunnel;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import protocol.PipelineFactory;

public class Server {

    private final ServerBootstrap server = new ServerBootstrap();

    public void start() throws Exception {
        server.setFactory(new NioServerSocketChannelFactory(MineTunnel.executor, MineTunnel.executor));
        server.setPipelineFactory(new PipelineFactory());
        server.bind(new InetSocketAddress(MineTunnel.port));
    }
}
