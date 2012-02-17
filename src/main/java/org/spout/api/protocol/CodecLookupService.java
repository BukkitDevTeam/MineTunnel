package org.spout.api.protocol;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A class used to lookup message codecs.
 */
public abstract class CodecLookupService {

    /**
     * A table which maps opcodes to codecs. This is generally used to map
     * incoming packets to a codec.
     */
    protected final MessageCodec<?>[] opcodeTable = new MessageCodec<?>[256];
    protected final MessageCodec<?>[] expandedOpcodeTable = new MessageCodec<?>[65536];
    /**
     * A table which maps messages to codecs. This is generally used to map
     * outgoing packets to a codec.
     */
    protected final Map<Class<? extends Message>, MessageCodec<?>> classTable = new HashMap<Class<? extends Message>, MessageCodec<?>>();

    /**
     * Binds a codec by adding entries for it to the tables.
     *
     * @param clazz The codec's class.
     * @param <T> The type of message.
     * @param <C> The type of codec.
     * @throws InstantiationException if the codec could not be instantiated.
     * @throws IllegalAccessException if the codec could not be instantiated due
     * to an access violation.
     */
    protected <T extends Message, C extends MessageCodec<T>> void bind(Class<C> clazz) throws InstantiationException, IllegalAccessException {
        MessageCodec<T> codec = clazz.newInstance();

        if (codec.isExpanded()) {
            expandedOpcodeTable[codec.getOpcode()] = codec;
        } else {
            opcodeTable[codec.getOpcode()] = codec;
        }
        classTable.put(codec.getType(), codec);
    }

    /**
     * Finds a codec by short opcode.
     *
     * @param opcode The opcode.
     * @return The codec, or {@code null} if it could not be found.
     */
    private MessageCodec<?> findNotchian(int opcode) {
        if (opcode < 0 || opcode >= opcodeTable.length) {
            return null;
        } else {
            return opcodeTable[opcode];
        }
    }

    public MessageCodec<?> find(int opcode) {
        MessageCodec<?> codec = findNotchian(opcode >> 8);

        if (codec != null) {
            return codec;
        } else if (opcode > -1 && opcode < expandedOpcodeTable.length) {
            return expandedOpcodeTable[opcode];
        }
        return null;
    }

    /**
     * Finds a codec by message class.
     *
     * @param clazz The message class.
     * @param <T> The type of message.
     * @return The codec, or {@code null} if it could not be found.
     */
    public <T extends Message> MessageCodec<T> find(Class<T> clazz) {
        return (MessageCodec<T>) classTable.get(clazz);
    }

    public Collection<MessageCodec<?>> getCodecs() {
        return Collections.unmodifiableCollection(classTable.values());
    }
}
