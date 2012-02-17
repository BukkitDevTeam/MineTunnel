package org.spout.api.protocol.bootstrap;

import org.spout.api.protocol.CodecLookupService;
import org.spout.api.protocol.HandlerLookupService;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.Protocol;

public abstract class BootstrapProtocol extends Protocol {

    public BootstrapProtocol(String name, CodecLookupService codecLookup, HandlerLookupService handlerLookup) {
        super(name, codecLookup, handlerLookup);
    }

    public abstract long detectProtocolDefinition(Message message);
}
