package protocol;

import handler.ServerListPingMessageHandler;
import java.util.HashMap;
import java.util.Map;
import message.ServerListPingMessage;

public class HandlerLookupService {

    private static final Map<Class<? extends Message>, MessageHandler<?>> handlers = new HashMap<Class<? extends Message>, MessageHandler<?>>();

    static {
        try {
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
