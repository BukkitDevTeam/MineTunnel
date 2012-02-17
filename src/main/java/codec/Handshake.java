package codec;

import message.HandshakeMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;
import protocol.ChannelBufferUtils;

public final class Handshake extends MessageCodec<HandshakeMessage> {

    public Handshake() {
        super(HandshakeMessage.class, 2);
    }

    @Override
    public HandshakeMessage decode(ChannelBuffer buffer) {
        String identifier = ChannelBufferUtils.readString(buffer);
        return new HandshakeMessage(identifier);
    }

    @Override
    public ChannelBuffer encode(HandshakeMessage message) {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        ChannelBufferUtils.writeString(buffer, message.getIdentifier());
        return buffer;
    }
}
