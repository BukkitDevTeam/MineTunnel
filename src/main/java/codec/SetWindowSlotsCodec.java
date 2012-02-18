package codec;

import java.io.IOException;
import java.util.Map;
import message.SetWindowSlotsMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.spout.nbt.Tag;
import protocol.MessageCodec;
import spout.ChannelBufferUtils;
import spout.ItemStack;
import spout.MaterialData;

public final class SetWindowSlotsCodec extends MessageCodec<SetWindowSlotsMessage> {

    public SetWindowSlotsCodec() {
        super(SetWindowSlotsMessage.class, 0x68);
    }

    @Override
    public SetWindowSlotsMessage decode(ChannelBuffer buffer) throws IOException {
        int id = buffer.readUnsignedByte();
        int count = buffer.readUnsignedShort();
        ItemStack[] items = new ItemStack[count];
        for (int slot = 0; slot < count; slot++) {
            int item = buffer.readUnsignedShort();
            if (item == 0xFFFF) {
                items[slot] = null;
            } else {
                int itemCount = buffer.readUnsignedByte();
                int damage = buffer.readUnsignedByte();
                Map<String, Tag> nbtData = null;
                if (ChannelBufferUtils.hasNbtData(item)) {
                    nbtData = ChannelBufferUtils.readCompound(buffer);
                }
                items[slot] = new ItemStack(MaterialData.getMaterial((short) item, (byte) damage), itemCount, (short) damage).setAuxData(nbtData);
            }
        }
        return new SetWindowSlotsMessage(id, items);
    }

    @Override
    public ChannelBuffer encode(SetWindowSlotsMessage message) throws IOException {
        ItemStack[] items = message.getItems();

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeByte(message.getId());
        buffer.writeShort(items.length);
        for (ItemStack item : items) {
            if (item == null) {
                buffer.writeShort(-1);
            } else {
                buffer.writeShort(item.getMaterial().getId());
                buffer.writeByte(item.getAmount());
                buffer.writeByte(item.getDamage());
                if (ChannelBufferUtils.hasNbtData(item.getMaterial().getData())) {
                    ChannelBufferUtils.writeCompound(buffer, item.getAuxData());
                }
            }
        }
        return buffer;
    }
}
