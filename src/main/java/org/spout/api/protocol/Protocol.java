package org.spout.api.protocol;

import java.util.concurrent.ConcurrentHashMap;
import org.spout.vanilla.handler.HandlerLookupService;

public class Protocol {

    private static final ConcurrentHashMap<Long, Protocol> map = new ConcurrentHashMap<Long, Protocol>();
    private final CodecLookupService codecLookup;
    private final HandlerLookupService handlerLookup;
    private final String name;

    public Protocol(String name, CodecLookupService codecLookup, HandlerLookupService handlerLookup) {
        this.codecLookup = codecLookup;
        this.handlerLookup = handlerLookup;
        this.name = name;
    }

    /**
     * Gets the handler lookup service associated with this Protocol
     *
     * @return the handler lookup service
     */
    public HandlerLookupService getHandlerLookupService() {
        return handlerLookup;
    }

    /**
     * Gets the codec lookup service associated with this Protocol
     *
     * @return the codec lookup service
     */
    public CodecLookupService getCodecLookupService() {
        return codecLookup;
    }


    /**
     * Gets the name of the Protocol
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Registers a Protocol for a particular id value
     *
     * @param id the id
     * @param protocol the Protocol
     */
    public static void registerProtocol(long id, Protocol protocol) {
        map.put(id, protocol);
    }

    /**
     * Gets the Protocol associated with a particular id
     *
     * @param id the id
     * @return the Protocol
     */
    public static Protocol getProtocol(long id) {
        return map.get(id);
    }
}
