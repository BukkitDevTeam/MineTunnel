package com.md_5.minetunnel;

import com.md_5.minetunnel.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A list of all the sessions which provides a convenient {@link #pulse()}
 * method to pulse every session in one operation.
 *
 */
public final class SessionRegistry {

    /**
     * A list of the sessions.
     */
    private final ConcurrentMap<Session, Boolean> sessions = new ConcurrentHashMap<Session, Boolean>();

    /**
     * Pulses all the sessions.
     */
    public void pulse() {
        for (Session session : sessions.keySet()) {
            session.pulse();
        }
    }

    /**
     * Adds a new session.
     *
     * @param session The session to add.
     */
    public void add(Session session) {
        if (session instanceof Session) {
            System.out.println("Session added");
            sessions.put((Session) session, true);
        } else if (session != null) {
            throw new IllegalArgumentException("This session registry can only handle SpoutSessions: ");
        }
    }

    /**
     * Removes a session.
     *
     * @param session The session to remove.
     */
    public void remove(Session session) {
        if (session instanceof Session) {
            sessions.remove((Session) session);
        } else if (session != null) {
            throw new IllegalArgumentException("This session registry can only handle SpoutSessions");
        }
    }
}
