package org.spout.api.protocol;

import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import protocol.VanillaBootstrapProtocol;

/**
 * A {@link OneToOneEncoder} which encodes Minecraft {@link Message}s into
 * {@link ChannelBuffer}s.
 */
public class CommonEncoder extends OneToOneEncoder {

    private volatile CodecLookupService codecLookup = null;

    @SuppressWarnings("unchecked")
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel c, Object msg) throws Exception {
        if (msg instanceof Message) {
            if (codecLookup == null) {
                codecLookup = new VanillaBootstrapProtocol().getCodecLookupService();
            }
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

    public void setProtocol(Protocol protocol) {
        codecLookup = protocol.getCodecLookupService();
    }
}
