package codec;

import java.io.IOException;
import message.CloseWindowMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class CloseWindowCodec extends MessageCodec<CloseWindowMessage> {

    public CloseWindowCodec() {
        super(CloseWindowMessage.class, 0x65);
    }

    @Override
    public CloseWindowMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readUnsignedByte();
        return new CloseWindowMessage(id);
    }

    @Override
    public ChannelBuffer encode(CloseWindowMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(1);
        buffer.writeByte(message.getId());
        return buffer;
    }
}
