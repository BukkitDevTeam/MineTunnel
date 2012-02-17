package codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;
import protocol.ChannelBufferUtils;
import message.IdentificationMessage;

public final class IdentificationCodec extends MessageCodec<IdentificationMessage> {

    public IdentificationCodec() {
        super(IdentificationMessage.class, 0x01);
    }

    @Override
    public IdentificationMessage decode(ChannelBuffer buffer) {
        int version = buffer.readInt();
        String name = ChannelBufferUtils.readString(buffer);
        long seed = buffer.readLong();
        String worldType = ChannelBufferUtils.readString(buffer);
        int mode = buffer.readInt();
        int dimension = buffer.readByte();
        int difficulty = buffer.readByte();
        int worldHeight = ChannelBufferUtils.getExpandedHeight(buffer.readByte());
        int maxPlayers = buffer.readByte();
        return new IdentificationMessage(version, name, seed, mode, dimension, difficulty, worldHeight, maxPlayers, worldType);
    }

    @Override
    public ChannelBuffer encode(IdentificationMessage message) {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(message.getId());
        ChannelBufferUtils.writeString(buffer, message.getName());
        buffer.writeLong(message.getSeed());
        ChannelBufferUtils.writeString(buffer, message.getWorldType());
        buffer.writeInt(message.getGameMode());
        buffer.writeByte(message.getDimension());
        buffer.writeByte(message.getDifficulty());
        buffer.writeByte(ChannelBufferUtils.getShifts(message.getWorldHeight()) - 1);
        buffer.writeByte(message.getMaxPlayers());
        return buffer;
    }
}
