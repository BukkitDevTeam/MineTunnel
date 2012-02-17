package org.spout.api.protocol;

import org.spout.vanilla.protocol.msg.KickMessage;

public class PlayerProtocol {

    public Message getChatMessage(String message) {
        return null; // todo
    }

    public Message getKickMessage(String reason) {
        return new KickMessage(reason);
    }
}
