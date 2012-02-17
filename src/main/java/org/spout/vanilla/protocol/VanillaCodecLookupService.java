package org.spout.vanilla.protocol;

import org.spout.api.protocol.CodecLookupService;
import org.spout.vanilla.protocol.codec.*;

public class VanillaCodecLookupService extends CodecLookupService {

    public VanillaCodecLookupService() {
        try {
            bind(PingCodec.class);
            bind(IdentificationCodec.class);
            bind(HandshakeCodec.class);
            bind(RespawnCodec.class);
            bind(ServerListPingCodec.class);
            bind(KickCodec.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
