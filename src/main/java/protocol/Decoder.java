package protocol;

import server.ServerHandler;
import java.io.IOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;
import protocol.CodecLookupService;
import protocol.VanillaProtocol;

/**
 * A {@link ReplayingDecoder} which decodes {@link ChannelBuffer}s into Common {@link org.spout.api.protocol.Message}s.
 */
public class Decoder extends ReplayingDecoder<VoidEnum> {

    private volatile CodecLookupService codecLookup = null;
    private int previousOpcode = -1;
    private volatile VanillaProtocol bootstrapProtocol;
    private final ServerHandler handler;
    private final Encoder encoder;

    public Decoder(ServerHandler handler, Encoder encoder) {
        this.encoder = encoder;
        this.handler = handler;
    }

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

        previousOpcode = opcode;

        Message message = codec.decode(buf);

        if (bootstrapProtocol != null) {
            System.out.println("Checking for protocol definition");
            long id = bootstrapProtocol.detectProtocolDefinition(message);
            if (id != -1L) {
                Protocol protocol = Protocol.getProtocol(id);

                if (protocol != null) {
                    codecLookup = protocol.getCodecLookupService();
                    encoder.setProtocol(protocol);
                    handler.setProtocol(protocol);
                    bootstrapProtocol = null;
                } else {
                    throw new IllegalStateException("No protocol associated with an id of " + id);
                }
            }
        }

        return message;
    }
}
