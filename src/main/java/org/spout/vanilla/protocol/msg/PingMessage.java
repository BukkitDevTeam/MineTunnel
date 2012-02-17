package org.spout.vanilla.protocol.msg;

import org.spout.api.protocol.Message;

public final class PingMessage extends Message {

    private final int pingId;

    public PingMessage(int pingId) {
        this.pingId = pingId;
    }

    public int getPingId() {
        return pingId;
    }

    @Override
    public String toString() {
        return "PingMessage{id=" + pingId + "}";
    }
}
