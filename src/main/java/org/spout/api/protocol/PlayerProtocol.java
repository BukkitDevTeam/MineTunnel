package org.spout.api.protocol;

/**
 * A wrapper to allow alternate Message usage when sending various packets.
 */
public interface PlayerProtocol {

    /**
     * Returns a chat packet to target with message
     *
     * @param message The chat message that is trying to be sent
     * @return The message to send
     */
    public Message getChatMessage(String message);

    /**
     * Returns a kick packet with the desired kick reason.
     *
     * @param reason The reason for a kick.
     * @return The kick packet.
     */
    public Message getKickMessage(String reason);
}
