package codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;
import protocol.ChannelBufferUtils;
import message.KickMessage;

public final class KickCodec extends MessageCodec<KickMessage> {

    public KickCodec() {
        super(KickMessage.class, 0xFF);
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
