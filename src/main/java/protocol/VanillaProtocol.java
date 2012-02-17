package protocol;

import message.LoginMessage;

public class VanillaProtocol extends Protocol {

    public VanillaProtocol() {
        super("VanillaBootstrap", new CodecLookupService(), new HandlerLookupService());
    }

    public long detectProtocolDefinition(Message message) {
        System.out.println("Testing for protocol in message: " + message);
        if (message instanceof LoginMessage) {
            LoginMessage idMessage = (LoginMessage) message;
            return idMessage.getSeed();
        }
        return -1L;
    }
}
