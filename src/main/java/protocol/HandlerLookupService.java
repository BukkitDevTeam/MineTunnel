package protocol;

import handler.*;
import java.util.HashMap;
import java.util.Map;
import message.*;

public class HandlerLookupService {

    private static final Map<Class<? extends Message>, MessageHandler<?>> handlers = new HashMap<Class<? extends Message>, MessageHandler<?>>();

    static {
        try {
            bind(IdentificationMessage.class, IdentificationMessageHandler.class);
            bind(HandshakeMessage.class, HandshakeMessageHandler.class);
            bind(KickMessage.class, KickMessageHandler.class);
            bind(RespawnMessage.class, RespawnMessageHandler.class);
            bind(ServerListPingMessage.class, ServerListPingMessageHandler.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static <T extends Message> void bind(Class<T> clazz, Class<? extends MessageHandler<T>> handlerClass) throws InstantiationException, IllegalAccessException {
        MessageHandler<T> handler = handlerClass.newInstance();
        handlers.put(clazz, handler);
    }

    public static <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }
}
