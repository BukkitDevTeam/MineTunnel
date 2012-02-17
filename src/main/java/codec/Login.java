package codec;

import message.LoginMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;
import protocol.ChannelBufferUtils;

public final class Login extends MessageCodec<LoginMessage> {

    public Login() {
        super(LoginMessage.class, 1);
    }

    @Override
    public LoginMessage decode(ChannelBuffer buffer) {
        int version = buffer.readInt();
        String name = ChannelBufferUtils.readString(buffer);
        long seed = buffer.readLong();
        String worldType = ChannelBufferUtils.readString(buffer);
        int mode = buffer.readInt();
        int dimension = buffer.readByte();
        int difficulty = buffer.readByte();
        int worldHeight = ChannelBufferUtils.getExpandedHeight(buffer.readByte());
        int maxPlayers = buffer.readByte();
        return new LoginMessage(version, name, seed, mode, dimension, difficulty, worldHeight, maxPlayers, worldType);
    }

    @Override
    public ChannelBuffer encode(LoginMessage message) {
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
