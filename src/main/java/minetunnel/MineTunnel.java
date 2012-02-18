package minetunnel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import message.KickMessage;
import server.Server;

public class MineTunnel {

    // Config
    public static int PROTOCOL_VERSION = 23;
    public static int port = 25565;
    public static boolean offlineMode;
    public static String motd = "Proxy";
    // Internals
    public static final ExecutorService executor = Executors.newCachedThreadPool();
    private static final Server server = new Server();
    private static final ConcurrentMap<Session, Boolean> sessions = new ConcurrentHashMap<Session, Boolean>();

    public static void main(String[] args) throws Exception {
        server.start();
    }

    public static String getOnlinePlayers() {
        return "1";
    }

    public static String getMaxPlayers() {
        return "1";
    }

    public static KickMessage getPingMessage() {
        return new KickMessage(motd + "\u00A7" + getOnlinePlayers() + "\u00A7" + getMaxPlayers() + "\u00A7");
    }

    public static ConcurrentMap<Session, Boolean> getSessions() {
        return sessions;
    }
}
