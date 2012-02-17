package org.spout.api.protocol.bootstrap;

import org.spout.api.protocol.*;

public abstract class BootstrapProtocol extends Protocol {

    public BootstrapProtocol(String name, CodecLookupService codecLookup, HandlerLookupService handlerLookup) {
        super(name, codecLookup, handlerLookup, new PlayerProtocol());
    }

    public abstract long detectProtocolDefinition(Message message);
}
