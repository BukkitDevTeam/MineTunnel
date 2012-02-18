package codec;

import java.io.IOException;
import message.CustomDataMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;
import spout.ChannelBufferUtils;

public class CustomDataCodec extends MessageCodec<CustomDataMessage> {

    public CustomDataCodec() {
        super(CustomDataMessage.class, 0xFA);
    }

    @Override
    public CustomDataMessage decode(ChannelBuffer buffer) throws IOException {
        String type = ChannelBufferUtils.readString(buffer);
        int length = buffer.readUnsignedShort();
        byte[] data = new byte[length];
        buffer.readBytes(data);
        return new CustomDataMessage(type, data);
    }

    @Override
    public ChannelBuffer encode(CustomDataMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        ChannelBufferUtils.writeString(buffer, message.getType());
        buffer.writeShort(message.getData().length);
        buffer.writeBytes(message.getData());
        return buffer;
    }
}
