package org.spout.api.protocol.bootstrap;

import org.spout.api.protocol.Message;
import org.spout.api.protocol.PlayerProtocol;
import org.spout.vanilla.protocol.msg.KickMessage;

/**
 * A player protocol that does nothing.
 */
public class NullPlayerProtocol implements PlayerProtocol {

    public Message getChatMessage(String message) {
        return null; // todo
    }

    public Message getKickMessage(String reason) {
        return new KickMessage(reason);
    }
}
