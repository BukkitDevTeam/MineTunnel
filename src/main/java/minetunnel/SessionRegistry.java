package minetunnel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class SessionRegistry {

    private final ConcurrentMap<Session, Boolean> sessions = new ConcurrentHashMap<Session, Boolean>();

    public void pulse() {
        for (Session session : sessions.keySet()) {
            session.pulse();
        }
    }

    public void add(Session session) {
        System.out.println("Session added");
        sessions.put(session, true);
        session.pulse();
    }

    public void remove(Session session) {
        sessions.remove(session);
    }
}
