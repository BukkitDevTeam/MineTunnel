package codec;

import java.io.IOException;
import message.SpawnItemMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class SpawnItemCodec extends MessageCodec<SpawnItemMessage> {

    public SpawnItemCodec() {
        super(SpawnItemMessage.class, 0x15);
    }

    @Override
    public SpawnItemMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        int itemId = buffer.readUnsignedShort();
        int count = buffer.readUnsignedByte();
        int damage = buffer.readUnsignedShort();
        int x = buffer.readInt();
        int y = buffer.readInt();
        int z = buffer.readInt();
        int rotation = buffer.readUnsignedByte();
        int pitch = buffer.readUnsignedByte();
        int roll = buffer.readUnsignedByte();
        return new SpawnItemMessage(id, itemId, count, (short) damage, x, y, z, rotation, pitch, roll);
    }

    @Override
    public ChannelBuffer encode(SpawnItemMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(24);
        buffer.writeInt(message.getId());
        buffer.writeShort(message.getItemId());
        buffer.writeByte(message.getCount());
        buffer.writeShort(message.getDamage());
        buffer.writeInt(message.getX());
        buffer.writeInt(message.getY());
        buffer.writeInt(message.getZ());
        buffer.writeByte(message.getRotation());
        buffer.writeByte(message.getPitch());
        buffer.writeByte(message.getRoll());
        return buffer;
    }
}
