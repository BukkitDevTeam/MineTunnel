package message;

import protocol.Message;

public final class KeepAliveMessage extends Message {

    private final int pingId;

    public KeepAliveMessage(int pingId) {
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
