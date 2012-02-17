package org.spout.vanilla.protocol;

import org.spout.api.protocol.HandlerLookupService;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;
import org.spout.vanilla.protocol.handler.*;
import org.spout.vanilla.protocol.msg.*;

public class VanillaHandlerLookupService extends HandlerLookupService {

    public VanillaHandlerLookupService() {
        super();
    }

    static {
        try {
            bind(IdentificationMessage.class, IdentificationMessageHandler.class);
            bind(HandshakeMessage.class, HandshakeMessageHandler.class);
            bind(KickMessage.class, KickMessageHandler.class);
            bind(PingMessage.class, PingMessageHandler.class);
            bind(RespawnMessage.class, RespawnMessageHandler.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected static <T extends Message> void bind(Class<T> clazz, Class<? extends MessageHandler<T>> handlerClass) throws InstantiationException, IllegalAccessException {
        MessageHandler<T> handler = handlerClass.newInstance();
        handlers.put(clazz, handler);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }
}
