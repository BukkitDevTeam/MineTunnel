package org.spout.vanilla.protocol.bootstrap;

import org.spout.api.protocol.CodecLookupService;
import org.spout.vanilla.protocol.codec.HandshakeCodec;
import org.spout.vanilla.protocol.codec.IdentificationCodec;
import org.spout.vanilla.protocol.codec.KickCodec;
import org.spout.vanilla.protocol.codec.ServerListPingCodec;

public class BootstrapCodecLookupService extends CodecLookupService {

    public BootstrapCodecLookupService() {
        super();
        try {
            bind(IdentificationCodec.class);
            bind(HandshakeCodec.class);
            bind(ServerListPingCodec.class);
            bind(KickCodec.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
