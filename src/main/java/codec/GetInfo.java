package codec;

import java.io.IOException;
import message.GetInfoMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.api.protocol.MessageCodec;

public class GetInfo extends MessageCodec<GetInfoMessage> {

    public GetInfo() {
        super(GetInfoMessage.class, 254);
    }

    @Override
    public GetInfoMessage decode(ChannelBuffer buffer) throws IOException {
        return new GetInfoMessage();
    }

    @Override
    public ChannelBuffer encode(GetInfoMessage message) throws IOException {
        return ChannelBuffers.EMPTY_BUFFER;
    }
}
