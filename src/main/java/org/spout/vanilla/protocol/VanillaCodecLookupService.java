package org.spout.vanilla.protocol;

import codec.ServerListPingCodec;
import codec.KickCodec;
import codec.RespawnCodec;
import codec.IdentificationCodec;
import codec.PingCodec;
import codec.HandshakeCodec;
import org.spout.api.protocol.CodecLookupService;

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
