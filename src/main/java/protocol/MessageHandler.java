package protocol;

import minetunnel.Session;

public abstract class MessageHandler<T extends Message> {

    public abstract void handle(Session session, T message);

    public void handleServer(Session session, T message) {
    }

    public void handleClient(Session session, T message) {
    }
}
