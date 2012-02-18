package codec;

import java.io.IOException;
import message.TimeMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class TimeCodec extends MessageCodec<TimeMessage> {

    public TimeCodec() {
        super(TimeMessage.class, 0x04);
    }

    @Override
    public TimeMessage decode(ChannelBuffer buffer) throws IOException {
        return new TimeMessage(buffer.readLong());
    }

    @Override
    public ChannelBuffer encode(TimeMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(8);
        buffer.writeLong(message.getTime());
        return buffer;
    }
}
