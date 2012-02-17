package org.spout.vanilla.protocol.bootstrap;

import org.spout.api.protocol.CodecLookupService;
import codec.HandshakeCodec;
import codec.IdentificationCodec;
import codec.KickCodec;
import codec.ServerListPingCodec;

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
