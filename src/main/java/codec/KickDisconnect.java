package codec;

import message.KickMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;
import protocol.ChannelBufferUtils;

public final class KickDisconnect extends MessageCodec<KickMessage> {

    public KickDisconnect() {
        super(KickMessage.class, 255);
    }

    @Override
    public KickMessage decode(ChannelBuffer buffer) {
        return new KickMessage(ChannelBufferUtils.readString(buffer));
    }

    @Override
    public ChannelBuffer encode(KickMessage message) {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        ChannelBufferUtils.writeString(buffer, message.getReason());
        return buffer;
    }
}
