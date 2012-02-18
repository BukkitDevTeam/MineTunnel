package codec;

import message.PingMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class PingCodec extends MessageCodec<PingMessage> {

    public PingCodec() {
        super(PingMessage.class, 0x00);
    }

    @Override
    public PingMessage decode(ChannelBuffer buffer) {
        int id = buffer.readInt();
        return new PingMessage(id);
    }

    @Override
    public ChannelBuffer encode(PingMessage message) {
        ChannelBuffer buffer = ChannelBuffers.buffer(4);
        buffer.writeInt(message.getPingId());
        return buffer;
    }
}
