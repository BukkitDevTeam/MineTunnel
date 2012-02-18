package protocol;

import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class Encoder extends OneToOneEncoder {

    private volatile CodecLookupService codecLookup = new CodecLookupService();

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel c, Object msg) throws Exception {
        if (msg instanceof Message) {
            Message message = (Message) msg;

            Class<? extends Message> clazz = message.getClass();
            MessageCodec<Message> codec;

            codec = (MessageCodec<Message>) codecLookup.find(clazz);
            if (codec == null) {
                throw new IOException("Unknown message type: " + clazz + ".");
            }

            ChannelBuffer opcodeBuf = ChannelBuffers.buffer(codec.isExpanded() ? 2 : 1);
            if (codec.isExpanded()) {
                opcodeBuf.writeShort(codec.getOpcode());
            } else {
                opcodeBuf.writeByte(codec.getOpcode());
            }

            return ChannelBuffers.wrappedBuffer(opcodeBuf, codec.encode(message));
        }
        return msg;
    }
}
