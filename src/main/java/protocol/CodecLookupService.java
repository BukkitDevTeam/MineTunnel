package protocol;

import codec.*;
import java.util.HashMap;
import java.util.Map;

public final class CodecLookupService {

    public CodecLookupService() {
        try {
            bind(PingCodec.class);
            bind(HandshakeCodec.class);
            bind(ChatCodec.class);
            bind(RespawnCodec.class);
            bind(CustomDataCodec.class);
            bind(ServerListPingCodec.class);
            bind(KickCodec.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    protected final MessageCodec<?>[] opcodeTable = new MessageCodec<?>[256];
    protected final MessageCodec<?>[] expandedOpcodeTable = new MessageCodec<?>[65536];
    protected final Map<Class<? extends Message>, MessageCodec<?>> classTable = new HashMap<Class<? extends Message>, MessageCodec<?>>();

    private <T extends Message, C extends MessageCodec<T>> void bind(Class<C> clazz) throws InstantiationException, IllegalAccessException {
        MessageCodec<T> codec = clazz.newInstance();

        if (codec.isExpanded()) {
            expandedOpcodeTable[codec.getOpcode()] = codec;
        } else {
            opcodeTable[codec.getOpcode()] = codec;
        }
        classTable.put(codec.getType(), codec);
    }

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

    @SuppressWarnings("unchecked")
    public <T extends Message> MessageCodec<T> find(Class<T> clazz) {
        return (MessageCodec<T>) classTable.get(clazz);
    }
}
