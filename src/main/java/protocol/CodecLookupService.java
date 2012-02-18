package protocol;

import codec.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CodecLookupService {

    public CodecLookupService() {
        try {
            bind(PingCodec.class);
            bind(IdentificationCodec.class);
            bind(HandshakeCodec.class);
            bind(ChatCodec.class);
            bind(TimeCodec.class);
            bind(EntityEquipmentCodec.class);
            bind(SpawnPositionCodec.class);
            bind(EntityInteractionCodec.class);
            bind(HealthCodec.class);
            bind(RespawnCodec.class);
            bind(GroundCodec.class);
            bind(PositionCodec.class);
            bind(RotationCodec.class);
            bind(PositionRotationCodec.class);
            bind(DiggingCodec.class);
            bind(BlockPlacementCodec.class);
            bind(ActivateItemCodec.class);
            bind(UseBedCodec.class);
            bind(AnimateEntityCodec.class);
            bind(EntityActionCodec.class);
            bind(SpawnPlayerCodec.class);
            bind(SpawnItemCodec.class);
            bind(CollectItemCodec.class);
            bind(SpawnVehicleCodec.class);
            bind(SpawnMobCodec.class);
            bind(SpawnPaintingCodec.class);
            bind(ExperienceOrbCodec.class);
            bind(EntityVelocityCodec.class);
            bind(DestroyEntityCodec.class);
            bind(CreateEntityCodec.class);
            bind(RelativeEntityPositionCodec.class);
            bind(EntityRotationCodec.class);
            bind(RelativeEntityPositionRotationCodec.class);
            bind(EntityTeleportCodec.class);
            bind(EntityStatusCodec.class);
            bind(AttachEntityCodec.class);
            bind(EntityMetadataCodec.class);
            bind(EntityEffectCodec.class);
            bind(EntityRemoveEffectCodec.class);
            bind(ExperienceCodec.class);
            bind(LoadChunkCodec.class);
            bind(CompressedChunkCodec.class);
            bind(MultiBlockChangeCodec.class);
            bind(BlockChangeCodec.class);
            bind(PlayNoteCodec.class);
            bind(ExplosionCodec.class);
            bind(PlayEffectCodec.class);
            bind(StateChangeCodec.class);
            bind(SpawnLightningStrikeCodec.class);
            bind(OpenWindowCodec.class);
            bind(CloseWindowCodec.class);
            bind(WindowClickCodec.class);
            bind(SetWindowSlotCodec.class);
            bind(SetWindowSlotsCodec.class);
            bind(ProgressBarCodec.class);
            bind(TransactionCodec.class);
            bind(QuickBarCodec.class);
            bind(EnchantItemCodec.class);
            bind(UpdateSignCodec.class);
            bind(MapDataCodec.class);
            bind(StatisticCodec.class);
            bind(UserListItemCodec.class);
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
