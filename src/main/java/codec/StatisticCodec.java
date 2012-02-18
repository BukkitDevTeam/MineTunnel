package codec;

import java.io.IOException;
import message.StatisticMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public final class StatisticCodec extends MessageCodec<StatisticMessage> {

    public StatisticCodec() {
        super(StatisticMessage.class, 0xC8);
    }

    @Override
    public StatisticMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readInt();
        byte amount = buffer.readByte();
        return new StatisticMessage(id, amount);
    }

    @Override
    public ChannelBuffer encode(StatisticMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(5);
        buffer.writeInt(message.getId());
        buffer.writeByte(message.getAmount());
        return buffer;
    }
}
