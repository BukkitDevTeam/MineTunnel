package codec;

import java.io.IOException;
import message.RelativeEntityPositionRotationMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class RelativeEntityPositionRotationCodec extends MessageCodec<RelativeEntityPositionRotationMessage> {

    public RelativeEntityPositionRotationCodec() {
        super(RelativeEntityPositionRotationMessage.class, 0x21);
    }

    @Override
    public RelativeEntityPositionRotationMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        int dx = buffer.readByte();
        int dy = buffer.readByte();
        int dz = buffer.readByte();
        int rotation = buffer.readUnsignedByte();
        int pitch = buffer.readUnsignedByte();
        return new RelativeEntityPositionRotationMessage(id, dx, dy, dz, rotation, pitch);
    }

    @Override
    public ChannelBuffer encode(RelativeEntityPositionRotationMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(9);
        buffer.writeInt(message.getId());
        buffer.writeByte(message.getDeltaX());
        buffer.writeByte(message.getDeltaY());
        buffer.writeByte(message.getDeltaZ());
        buffer.writeByte(message.getRotation());
        buffer.writeByte(message.getPitch());
        return buffer;
    }
}
