package org.spout.vanilla.handler;

import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;
import handler.IdentificationMessageHandler;
import message.IdentificationMessage;

public final class HandlerLookupService extends org.spout.api.protocol.HandlerLookupService {

    public HandlerLookupService() {
        super();
    }

    static {
        try {
            bind(IdentificationMessage.class, IdentificationMessageHandler.class);
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
