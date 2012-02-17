package protocol;

import message.IdentificationMessage;
import org.spout.api.protocol.Message;
import org.spout.api.protocol.Protocol;

public class VanillaBootstrapProtocol extends Protocol {

    public VanillaBootstrapProtocol() {
        super("VanillaBootstrap", new VanillaCodecLookupService(), new VanillaHandlerLookupService());
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
