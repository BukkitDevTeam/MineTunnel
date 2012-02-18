package minetunnel;

import client.Client;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Random;
import message.KickMessage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import protocol.HandlerLookupService;
import protocol.Message;
import protocol.MessageHandler;

public class Session {

    private Channel clientChannel;
    private Client serverConnection;
    private Channel serverChannel;
    private final String sessionId = Long.toString(new Random().nextLong(), 16).trim();

    public Session(Channel clientChannel) {
        this.clientChannel = clientChannel;
        this.serverConnection = new Client(this);
        serverConnection.start("127.0.01", 25566);
    }

    public void send(Object message) {
        clientChannel.write(message);
    }

    public void disconnect(String reason) {
        clientChannel.write(new KickMessage(reason)).addListener(ChannelFutureListener.CLOSE);
    }

    public InetSocketAddress getAddress() {
        return (InetSocketAddress) clientChannel.getRemoteAddress();
    }

    public <T extends Message> void messageReceived(T message) {
        MessageHandler<Message> handler = (MessageHandler<Message>) HandlerLookupService.find(message.getClass());
        if (handler != null) {
            try {
                handler.handle(this, message);
            } catch (Exception ex) {
                ex.printStackTrace();
                disconnect("Message handler exception for " + message.getClass().getSimpleName());
            }
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public Client getClientConnection() {
        return serverConnection;
    }

    public void sendServer(Object message) {
        if (serverChannel != null) {
            serverChannel.write(message);
        } else {
            queue.add(message);
        }
    }
    private final ArrayList<Object> queue = new ArrayList<Object>();

    public void connected(Channel channel) {
        serverChannel = channel;
        for (Object message : queue) {
            sendServer(message);
        }
    }
}
