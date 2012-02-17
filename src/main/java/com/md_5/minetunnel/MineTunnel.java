package com.md_5.minetunnel;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.spout.api.protocol.CommonPipelineFactory;

public class MineTunnel {

    // Config
    public static int PROTOCOL_VERSION = 23;
    public static int port = 25565;
    public static boolean offlineMode;
    public static String motd = "Proxy";
    // Internals
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final ServerBootstrap bootstrap = new ServerBootstrap();
    private static SessionRegistry sessions = new SessionRegistry();

    public static void main(String[] args) throws Exception {
        new MineTunnel().start();
    }

    public void start() throws Exception {
        bootstrap.setFactory(new NioServerSocketChannelFactory(executor, executor));
        bootstrap.setPipelineFactory(new CommonPipelineFactory());
        bootstrap.bind(new InetSocketAddress(port));
        while (true) {
            sessions.pulse();
            Thread.sleep(500);
        }
    }

    public static String getOnlinePlayers() {
        return "1";
    }

    public static String getMaxPlayers() {
        return "1";
    }

    public static SessionRegistry getSessionRegistry() {
        return sessions;
    }
}