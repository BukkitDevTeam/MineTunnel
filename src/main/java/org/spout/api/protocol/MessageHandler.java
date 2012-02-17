package org.spout.api.protocol;

import com.md_5.minetunnel.Player;
import com.md_5.minetunnel.Session;

public abstract class MessageHandler<T extends Message> {

    /**
     * Handles a message. If the message is a one way method, then this method
     * can be overriden.
     *
     * Otherwise, it will call handleServer or handleClient as required.
     *
     * @param session the network session
     * @param player the player
     * @param message the message that was received
     */
    public void handle(Session session, Player player, T message) {
        handleServer(session, player, message);
    }

    /**
     * Handles a message.
     *
     * If handle is not overriden, then this method is called when a packet is
     * received from the client by the server.
     *
     * @param session the network session
     * @param player the player
     * @param message the message that was received
     */
    public void handleServer(Session session, Player player, T message) {
    }

    /**
     * Handles a message.
     *
     * If handle is not overriden, then this method is called when a packet is
     * received from the server by the client.
     *
     * @param session the network session
     * @param player the player
     * @param message the message that was received
     */
    public void handleClient(Session session, Player player, T message) {
    }
}
