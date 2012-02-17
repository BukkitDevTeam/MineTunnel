package protocol;

import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

public class Decoder extends ReplayingDecoder<VoidEnum> {

    private volatile CodecLookupService codecLookup = null;
    private int previousOpcode = -1;
    private volatile VanillaProtocol bootstrapProtocol;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel c, ChannelBuffer buf, VoidEnum state) throws Exception {
        if (codecLookup == null) {
            System.out.println("Setting codec lookup service");
            bootstrapProtocol = new VanillaProtocol();
            System.out.println("Bootstrap protocol is: " + bootstrapProtocol);
            codecLookup = bootstrapProtocol.getCodecLookupService();
            System.out.println("Codec lookup service is: " + codecLookup);
        }

        int opcode;
        try {
            opcode = buf.getUnsignedShort(buf.readerIndex());
        } catch (Error e) {
            opcode = buf.getUnsignedByte(buf.readerIndex()) << 8;
        }

        MessageCodec<?> codec = codecLookup.find(opcode);
        if (codec == null) {
            throw new IOException("Unknown operation code: " + opcode + " (previous opcode: " + previousOpcode + ").");
        }

        if (codec.isExpanded()) {
            buf.readShort();
        } else {
            buf.readByte();
        }

        Message message = codec.decode(buf);

        return message;
    }
}
