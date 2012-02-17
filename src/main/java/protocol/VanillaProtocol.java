package protocol;

import message.IdentificationMessage;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.Protocol;

public class VanillaProtocol extends Protocol {

    public VanillaProtocol() {
        super("VanillaBootstrap", new CodecLookupService(), new HandlerLookupService());
    }

    public long detectProtocolDefinition(Message message) {
        System.out.println("Testing for protocol in message: " + message);
        if (message instanceof IdentificationMessage) {
            IdentificationMessage idMessage = (IdentificationMessage) message;
            return idMessage.getSeed();
        }
        return -1L;
    }
}
