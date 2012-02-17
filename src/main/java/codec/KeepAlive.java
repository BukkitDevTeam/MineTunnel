package codec;

import message.KeepAliveMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

public final class KeepAlive extends MessageCodec<KeepAliveMessage> {

    public KeepAlive() {
        super(KeepAliveMessage.class, 0);
    }

    @Override
    public KeepAliveMessage decode(ChannelBuffer buffer) {
        int id = buffer.readInt();
        return new KeepAliveMessage(id);
    }

    @Override
    public ChannelBuffer encode(KeepAliveMessage message) {
        ChannelBuffer buffer = ChannelBuffers.buffer(4);
        buffer.writeInt(message.getPingId());
        return buffer;
    }
}
