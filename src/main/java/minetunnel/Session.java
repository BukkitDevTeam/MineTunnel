package minetunnel;

import client.Client;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Random;
import message.KickMessage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import protocol.HandlerLookupService;
import protocol.Message;
import protocol.MessageHandler;

public class Session {

    private final Channel clientChannel;
    private Client clientConnection;
    private final String sessionId = Long.toString(new Random().nextLong(), 16).trim();

    public Session(Channel clientChannel, Client clientConnection) {
        this.clientChannel = clientChannel;
        this.clientConnection = clientConnection;
    }

    public void send(Message message) {
        clientChannel.write(message);
    }

    public void disconnect(String reason) {
        clientChannel.write(new KickMessage(reason)).addListener(ChannelFutureListener.CLOSE);
    }

    public InetSocketAddress getAddress() {
        SocketAddress addr = clientChannel.getRemoteAddress();
        if (addr instanceof InetSocketAddress) {
            return (InetSocketAddress) addr;
        } else {
            return null;
        }
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
}
