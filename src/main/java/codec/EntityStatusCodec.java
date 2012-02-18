package codec;

import java.io.IOException;
import message.EntityStatusMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class EntityStatusCodec extends MessageCodec<EntityStatusMessage> {

    public EntityStatusCodec() {
        super(EntityStatusMessage.class, 0x26);
    }

    @Override
    public EntityStatusMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        int status = buffer.readUnsignedByte();
        return new EntityStatusMessage(id, status);
    }

    @Override
    public ChannelBuffer encode(EntityStatusMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(5);
        buffer.writeInt(message.getId());
        buffer.writeByte(message.getStatus());
        return buffer;
    }
}
