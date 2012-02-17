package org.spout.vanilla.protocol;

import handler.*;
import message.*;
import org.spout.api.protocol.HandlerLookupService;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;

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
    public <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }
}
