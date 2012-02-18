package codec;

import java.io.IOException;
import java.util.Map;
import message.BlockPlacementMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.nbt.Tag;
import protocol.MessageCodec;
import spout.ChannelBufferUtils;

public final class BlockPlacementCodec extends MessageCodec<BlockPlacementMessage> {

    public BlockPlacementCodec() {
        super(BlockPlacementMessage.class, 0x0F);
    }

    @Override
    public BlockPlacementMessage decode(ChannelBuffer buffer) throws IOException {
        int x = buffer.readInt();
        int y = buffer.readUnsignedByte();
        int z = buffer.readInt();
        int direction = buffer.readUnsignedByte();
        int id = buffer.readUnsignedShort();
        if (id == 0xFFFF) {
            return new BlockPlacementMessage(x, y, z, direction);
        } else {
            int count = buffer.readUnsignedByte();
            int damage = buffer.readShort();
            Map<String, Tag> nbtData = null;
            //if (ChannelBufferUtils.hasNbtData(id)) {
            nbtData = ChannelBufferUtils.readCompound(buffer);
            //} TODO
            return new BlockPlacementMessage(x, y, z, direction, id, count, damage, nbtData);
        }
    }

    @Override
    public ChannelBuffer encode(BlockPlacementMessage message) throws IOException {
        int id = message.getId();

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(message.getX());
        buffer.writeByte(message.getY());
        buffer.writeInt(message.getZ());
        buffer.writeByte(message.getDirection());
        buffer.writeShort(id);
        if (id != -1) {
            buffer.writeByte(message.getCount());
            buffer.writeShort(message.getDamage());
            if (ChannelBufferUtils.hasNbtData(id)) {
                ChannelBufferUtils.writeCompound(buffer, message.getNbtData());
            }
        }
        return buffer;
    }
}
