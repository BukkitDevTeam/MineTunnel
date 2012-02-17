package org.spout.vanilla.protocol.bootstrap;

import org.spout.api.protocol.HandlerLookupService;
import org.spout.vanilla.protocol.bootstrap.handler.BootstrapHandshakeMessageHandler;
import org.spout.vanilla.protocol.bootstrap.handler.BootstrapIdentificationMessageHandler;
import org.spout.vanilla.protocol.bootstrap.handler.BootstrapPingMessageHandler;
import message.HandshakeMessage;
import message.IdentificationMessage;
import message.ServerListPingMessage;

public class BootstrapHandlerLookupService extends HandlerLookupService {

    public BootstrapHandlerLookupService() {
        try {
            bind(HandshakeMessage.class, BootstrapHandshakeMessageHandler.class);
            bind(IdentificationMessage.class, BootstrapIdentificationMessageHandler.class);
            bind(ServerListPingMessage.class, BootstrapPingMessageHandler.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
