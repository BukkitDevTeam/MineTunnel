package codec;

import java.io.IOException;
import message.PassthroughMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import protocol.MessageCodec;
import spout.PacketScan;

public class PassthroughCodec extends MessageCodec<PassthroughMessage> {

    @Override
    public PassthroughMessage decode(ChannelBuffer buffer) throws IOException {
        PassthroughMessage pp = new PassthroughMessage();
        PacketScan.packetScan(buffer.array(), start, dataLength, mask, buffer);
        return pp;
    }

    @Override
    public ChannelBuffer encode(PassthroughMessage message) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class p {

        byte[] buffer;
    }
}
