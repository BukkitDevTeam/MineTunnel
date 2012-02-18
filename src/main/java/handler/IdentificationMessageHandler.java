package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import message.IdentificationMessage;
import minetunnel.MineTunnel;
import minetunnel.Session;
import protocol.MessageHandler;

public final class IdentificationMessageHandler extends MessageHandler<IdentificationMessage> {

    @Override
    public void handle(Session session, IdentificationMessage message) {
        if (message.getId() < MineTunnel.PROTOCOL_VERSION) {
            session.disconnect("Outdated client!");
        } else if (message.getId() > MineTunnel.PROTOCOL_VERSION) {
            session.disconnect("Outdated server!");
        }
        boolean allow = true;

        if (!MineTunnel.offlineMode) {
            allow = false;
            try {
                URL verify = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(message.getName(), "UTF-8") + "&serverId=" + URLEncoder.encode(session.getSessionId(), "UTF-8"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(verify.openStream()));
                String result = reader.readLine();
                reader.close();
                allow = result.equals("YES");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (allow) {
        } else {
        }
    }
}
