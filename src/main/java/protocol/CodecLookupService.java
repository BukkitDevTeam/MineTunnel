package protocol;

import codec.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CodecLookupService {

    public CodecLookupService() {
        try {
            /*
             * 0x00
             */ bind(PingCodec.class);
            /*
             * 0x01
             */ bind(IdentificationCodec.class);
            /*
             * 0x02
             */ bind(HandshakeCodec.class);
            /*
             * 0x03
             */ bind(ChatCodec.class);
            /*
             * 0x04
             */ bind(TimeCodec.class);
            /*
             * 0x05
             */ bind(EntityEquipmentCodec.class);
            /*
             * 0x06
             */ bind(SpawnPositionCodec.class);
            /*
             * 0x07
             */ bind(EntityInteractionCodec.class);
            /*
             * 0x08
             */ bind(HealthCodec.class);
            /*
             * 0x09
             */ bind(RespawnCodec.class);
            /*
             * 0x0A
             */ bind(GroundCodec.class);
            /*
             * 0x0B
             */ bind(PositionCodec.class);
            /*
             * 0x0C
             */ bind(RotationCodec.class);
            /*
             * 0x0D
             */ bind(PositionRotationCodec.class);
            /*
             * 0x0E
             */ bind(DiggingCodec.class);
            /*
             * 0x0F
             */ bind(BlockPlacementCodec.class);
            /*
             * 0x10
             */ bind(ActivateItemCodec.class);
            /*
             * 0x11
             */ bind(UseBedCodec.class);
            /*
             * 0x12
             */ bind(AnimateEntityCodec.class);
            /*
             * 0x13
             */ bind(EntityActionCodec.class);
            /*
             * 0x14
             */ bind(SpawnPlayerCodec.class);
            /*
             * 0x15
             */ bind(SpawnItemCodec.class);
            /*
             * 0x16
             */ bind(CollectItemCodec.class);
            /*
             * 0x17
             */ bind(SpawnVehicleCodec.class);
            /*
             * 0x18
             */ bind(SpawnMobCodec.class);
            /*
             * 0x19
             */ bind(SpawnPaintingCodec.class);
            /*
             * 0x1A
             */ bind(ExperienceOrbCodec.class);
            /*
             * 0x1C
             */ bind(EntityVelocityCodec.class);
            /*
             * 0x1D
             */ bind(DestroyEntityCodec.class);
            /*
             * 0x1E
             */ bind(CreateEntityCodec.class);
            /*
             * 0x1F
             */ bind(RelativeEntityPositionCodec.class);
            /*
             * 0x20
             */ bind(EntityRotationCodec.class);
            /*
             * 0x21
             */ bind(RelativeEntityPositionRotationCodec.class);
            /*
             * 0x22
             */ bind(EntityTeleportCodec.class);
            /*
             * 0x26
             */ bind(EntityStatusCodec.class);
            /*
             * 0x27
             */ bind(AttachEntityCodec.class);
            /*
             * 0x28
             */ bind(EntityMetadataCodec.class);
            /*
             * 0x29
             */ bind(EntityEffectCodec.class);
            /*
             * 0x2A
             */ bind(EntityRemoveEffectCodec.class);
            /*
             * 0x2B
             */ bind(ExperienceCodec.class);
            /*
             * 0x32
             */ bind(LoadChunkCodec.class);
            /*
             * 0x33
             */ bind(CompressedChunkCodec.class);
            /*
             * 0x34
             */ bind(MultiBlockChangeCodec.class);
            /*
             * 0x35
             */ bind(BlockChangeCodec.class);
            /*
             * 0x36
             */ bind(PlayNoteCodec.class);
            /*
             * 0x3C
             */ bind(ExplosionCodec.class);
            /*
             * 0x3D
             */ bind(PlayEffectCodec.class);
            /*
             * 0x46
             */ bind(StateChangeCodec.class);
            /*
             * 0x47
             */ bind(SpawnLightningStrikeCodec.class);
            /*
             * 0x64
             */ bind(OpenWindowCodec.class);
            /*
             * 0x65
             */ bind(CloseWindowCodec.class);
            /*
             * 0x66
             */ bind(WindowClickCodec.class);
            /*
             * 0x67
             */ bind(SetWindowSlotCodec.class);
            /*
             * 0x68
             */ bind(SetWindowSlotsCodec.class);
            /*
             * 0x69
             */ bind(ProgressBarCodec.class);
            /*
             * 0x6A
             */ bind(TransactionCodec.class);
            /*
             * 0x6B
             */ bind(QuickBarCodec.class);
            /*
             * 0x6C
             */ bind(EnchantItemCodec.class);
            /*
             * 0x82
             */ bind(UpdateSignCodec.class);
            /*
             * 0x83
             */ bind(MapDataCodec.class);
            /*
             * 0xC8
             */ bind(StatisticCodec.class);
            /*
             * 0xC9
             */ bind(UserListItemCodec.class);
            /*
             * 0xFA
             */ bind(CustomDataCodec.class);
            /*
             * 0xFE
             */ bind(ServerListPingCodec.class);
            /*
             * 0xFF
             */ bind(KickCodec.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
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
