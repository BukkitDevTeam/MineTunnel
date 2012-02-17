package minetunnel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import message.KickMessage;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Protocol;
import protocol.VanillaProtocol;

public class Session {

    /**
     * The Random for this session
     */
    private final Random random = new Random();
    /**
     * The channel associated with this session.
     */
    private final Channel channel;
    /**
     * The player associated with this session (if there is one).
     */
    private Player player;
    /**
     * The random long used for client-server handshake
     */
    private final String sessionId = Long.toString(random.nextLong(), 16).trim();
    /**
     * The protocol for this session
     */
    private final AtomicReference<Protocol> protocol;
    private final VanillaProtocol bootstrapProtocol;

    /**
     * Creates a new session.
     *
     * @param channel The channel associated with this session.
     * @param bootstrapProtocol
     */
    public Session(Channel channel, VanillaProtocol bootstrapProtocol) {
        this.channel = channel;
        this.protocol = new AtomicReference<Protocol>(bootstrapProtocol);
        this.bootstrapProtocol = bootstrapProtocol;
    }

    /**
     * Gets the player associated with this session.
     *
     * @return The player, or {@code null} if no player is associated with it.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player associated with this session.
     *
     * @param player The new player.
     * @throws IllegalStateException if there is already a player associated
     * with this session.
     */
    public void setPlayer(Player player) {
        if (this.player != null) {
            throw new IllegalStateException();
        }

        this.player = player;
    }

    /**
     * Sends a message to the client.
     *
     * @param message The message.
     */
    public void send(Message message) {
        channel.write(message);
    }

    /**
     * Disconnects the session with the specified reason. This causes a kick
     * packet to be sent. When it has been delivered, the channel is closed.
     *
     * @param reason The reason for disconnection.
     */
    public void disconnect(String reason) {
        disconnect(reason, false);
    }

    /**
     * Disconnects the session with the specified reason. This causes a kick
     * packet to be sent. When it has been delivered, the channel is closed.
     *
     * @param reason The reason for disconnection.
     * @param overrideKick Whether to override the kick event.
     */
    public void disconnect(String reason, boolean overrideKick) {
        if (player != null && !overrideKick) {
            System.out.println("Player {0} kicked: {1}");
            dispose(false);
        }
        channel.write(new KickMessage(reason)).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * Returns the address of this session.
     *
     * @return The remote address.
     */
    public InetSocketAddress getAddress() {
        SocketAddress addr = channel.getRemoteAddress();
        if (addr instanceof InetSocketAddress) {
            return (InetSocketAddress) addr;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return Session.class.getName() + " [address=" + channel.getRemoteAddress() + "]";
    }

    /**
     * Adds a message to the unprocessed queue.
     *
     * @param message The message.
     * @param <T> The type of message.
     */
    public <T extends Message> void messageReceived(T message) {
        MessageHandler<Message> handler = (MessageHandler<Message>) this.protocol.get().getHandlerLookupService().find(message.getClass());
        if (handler != null) {
            try {
                handler.handle(this, player, message);
            } catch (Exception e) {
                System.out.println("Message handler for " + message.getClass().getSimpleName() + " threw exception for player " + this.getPlayer().getName());
                e.printStackTrace();
                disconnect("Message handler exception for " + message.getClass().getSimpleName());
            }
        }
    }

    public void dispose(boolean broadcastQuit) {
        if (player != null) {
            // has left the game
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setProtocol(Protocol protocol) {
        if (!this.protocol.compareAndSet(bootstrapProtocol, protocol)) {
            throw new IllegalArgumentException("The protocol may only be set once per session");
        } else {
            System.out.println("Setting protocol to " + protocol.getName());
        }
    }
}
