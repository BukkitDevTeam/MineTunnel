package codec;

import java.io.IOException;
import message.UseBedMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class UseBedCodec extends MessageCodec<UseBedMessage> {

    public UseBedCodec() {
        super(UseBedMessage.class, 0x11);
    }

    @Override
    public UseBedMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        int used = buffer.readUnsignedByte();
        int x = buffer.readInt();
        int y = buffer.readUnsignedByte();
        int z = buffer.readInt();
        return new UseBedMessage(id, used, x, y, z);
    }

    @Override
    public ChannelBuffer encode(UseBedMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(14);
        buffer.writeInt(message.getId());
        buffer.writeByte(message.getUsed());
        buffer.writeInt(message.getX());
        buffer.writeByte(message.getY());
        buffer.writeInt(message.getZ());
        return buffer;
    }
}
