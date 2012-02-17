package org.spout.server.net;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.spout.api.protocol.Session;

/**
 * A list of all the sessions which provides a convenient {@link #pulse()}
 * method to pulse every session in one operation.
 *
 */
public final class SpoutSessionRegistry {

    /**
     * A list of the sessions.
     */
    private final ConcurrentMap<SpoutSession, Boolean> sessions = new ConcurrentHashMap<SpoutSession, Boolean>();

    /**
     * Pulses all the sessions.
     */
    public void pulse() {
        for (SpoutSession session : sessions.keySet()) {
            session.pulse();
        }
    }

    /**
     * Adds a new session.
     *
     * @param session The session to add.
     */
    public void add(Session session) {
        if (session instanceof SpoutSession) {
            System.out.println("Session added");
            sessions.put((SpoutSession) session, true);
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
        if (session instanceof SpoutSession) {
            sessions.remove((SpoutSession) session);
        } else if (session != null) {
            throw new IllegalArgumentException("This session registry can only handle SpoutSessions");
        }
    }
}
