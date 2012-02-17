package org.spout.vanilla.protocol.bootstrap;

import org.spout.api.protocol.Message;
import org.spout.api.protocol.bootstrap.BootstrapProtocol;
import message.IdentificationMessage;

public class VanillaBootstrapProtocol extends BootstrapProtocol {

    public VanillaBootstrapProtocol() {
        super("VanillaBootstrap", new BootstrapCodecLookupService(), new BootstrapHandlerLookupService());
    }

    @Override
    public long detectProtocolDefinition(Message message) {
        System.out.println("Testing for protocol in message: " + message);
        if (message instanceof IdentificationMessage) {
            IdentificationMessage idMessage = (IdentificationMessage) message;
            return idMessage.getSeed();
        }
        return -1L;
    }
}
