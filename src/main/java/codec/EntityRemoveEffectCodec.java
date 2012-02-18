package codec;

import java.io.IOException;
import message.EntityRemoveEffectMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public class EntityRemoveEffectCodec extends MessageCodec<EntityRemoveEffectMessage> {

    public EntityRemoveEffectCodec() {
        super(EntityRemoveEffectMessage.class, 0x2A);
    }

    @Override
    public EntityRemoveEffectMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        byte effect = buffer.readByte();
        return new EntityRemoveEffectMessage(id, effect);
    }

    @Override
    public ChannelBuffer encode(EntityRemoveEffectMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(5);
        buffer.writeInt(message.getId());
        buffer.writeByte(message.getEffect());
        return buffer;
    }
}
