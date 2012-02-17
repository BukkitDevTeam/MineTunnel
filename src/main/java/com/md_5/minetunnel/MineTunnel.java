package com.md_5.minetunnel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.spout.api.protocol.CommonPipelineFactory;
import org.spout.api.protocol.bootstrap.BootstrapProtocol;

public class MineTunnel {

    public static int PROTOCOL_VERSION = 22;
    public static boolean offlineMode;
    public static String motd = "Proxy";
    private final ServerBootstrap bootstrap = new ServerBootstrap();

    public static void main(String[] args) {
        new MineTunnel().start();
    }
    private final ChannelGroup group = new DefaultChannelGroup();
    private final ConcurrentMap<SocketAddress, BootstrapProtocol> bootstrapProtocols = new ConcurrentHashMap<SocketAddress, BootstrapProtocol>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void start() {
        ChannelFactory factory = new NioServerSocketChannelFactory(executor, executor);
        bootstrap.setFactory(factory);
        ChannelPipelineFactory pipelineFactory = new CommonPipelineFactory();
        bootstrap.setPipelineFactory(pipelineFactory);
        bootstrap.bind(new InetSocketAddress(25565));

    }

    public static String getOnlinePlayers() {
        return "1";
    }

    public static String getMaxPlayers() {
        return "?";
    }
}
