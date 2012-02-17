package protocol;

import handler.*;
import message.*;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;
import org.spout.vanilla.handler.HandlerLookupService;

public class VanillaHandlerLookupService extends HandlerLookupService {

    public VanillaHandlerLookupService() {
        super();
    }

    static {
        try {
            bind(IdentificationMessage.class, IdentificationMessageHandler.class);
            bind(HandshakeMessage.class, HandshakeMessageHandler.class);
            bind(KickMessage.class, KickMessageHandler.class);
            bind(RespawnMessage.class, RespawnMessageHandler.class);
            bind(ServerListPingMessage.class, PingMessageHandler.class);
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
