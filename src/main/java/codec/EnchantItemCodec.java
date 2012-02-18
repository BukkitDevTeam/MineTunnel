package codec;

import java.io.IOException;
import message.EnchantItemMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import protocol.MessageCodec;

public class EnchantItemCodec extends MessageCodec<EnchantItemMessage> {

    public EnchantItemCodec() {
        super(EnchantItemMessage.class, 0x6C);
    }

    @Override
    public EnchantItemMessage decode(ChannelBuffer buffer) throws IOException {
        int transaction = buffer.readByte();
        int enchantment = buffer.readByte();
        return new EnchantItemMessage(transaction, enchantment);
    }

    @Override
    public ChannelBuffer encode(EnchantItemMessage message) throws IOException {
        ChannelBuffer buffer = ChannelBuffers.buffer(2);
        buffer.writeByte(message.getTransaction());
        buffer.writeByte(message.getEnchantment());
        return buffer;
    }
}
