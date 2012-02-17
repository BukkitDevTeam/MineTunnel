package codec;

import java.io.IOException;
import message.RespawnMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;
import protocol.ChannelBufferUtils;

public final class Respawn extends MessageCodec<RespawnMessage> {

    public Respawn() {
        super(RespawnMessage.class, 9);
    }

    @Override
    public RespawnMessage decode(ChannelBuffer buffer) throws IOException {
        byte dimension = buffer.readByte();
        byte difficulty = buffer.readByte();
        byte mode = buffer.readByte();
        int worldHeight = ChannelBufferUtils.getExpandedHeight(buffer.readShort());
        long seed = buffer.readLong();
        String worldType = ChannelBufferUtils.readString(buffer);
        return new RespawnMessage(dimension, difficulty, mode, worldHeight, seed, worldType);
    }

    @Override
    public ChannelBuffer encode(RespawnMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeByte(message.getDimension());
        buffer.writeByte(message.getDifficulty());
        buffer.writeByte(message.getGameMode());
        buffer.writeShort(ChannelBufferUtils.getShifts(message.getWorldHeight()) - 1);
        buffer.writeLong(message.getSeed());
        ChannelBufferUtils.writeString(buffer, message.getWorldType());
        return buffer;
    }
}
