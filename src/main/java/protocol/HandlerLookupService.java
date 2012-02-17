package protocol;

import handler.*;
import java.util.HashMap;
import java.util.Map;
import message.*;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.MessageHandler;

public class HandlerLookupService {

    protected static final Map<Class<? extends Message>, MessageHandler<?>> handlers = new HashMap<Class<? extends Message>, MessageHandler<?>>();

    static {
        try {
            bind(LoginMessage.class, LoginMessageHandler.class);
            bind(HandshakeMessage.class, HandshakeMessageHandler.class);
            bind(KickMessage.class, KickMessageHandler.class);
            bind(RespawnMessage.class, RespawnMessageHandler.class);
            bind(GetInfoMessage.class, GetInfoHandler.class);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected static <T extends Message> void bind(Class<T> clazz, Class<? extends MessageHandler<T>> handlerClass) throws InstantiationException, IllegalAccessException {
        MessageHandler<T> handler = handlerClass.newInstance();
        handlers.put(clazz, handler);
    }

    public <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }
}
