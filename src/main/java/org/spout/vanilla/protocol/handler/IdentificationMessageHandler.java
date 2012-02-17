package org.spout.vanilla.protocol.handler;

import com.md_5.minetunnel.MineTunnel;
import com.md_5.minetunnel.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import org.spout.api.protocol.MessageHandler;
import com.md_5.minetunnel.Session;
import org.spout.vanilla.protocol.msg.IdentificationMessage;

public final class IdentificationMessageHandler extends MessageHandler<IdentificationMessage> {

    @Override
    public void handle(Session session, Player player, IdentificationMessage message) {
        if (message.getId() < MineTunnel.PROTOCOL_VERSION) {
            session.disconnect("Outdated client!");
        } else if (message.getId() > MineTunnel.PROTOCOL_VERSION) {
            session.disconnect("Outdated server!");
        }
        boolean allow = true; // Default to okay
        // If we're in online mode, attempt to verify with mc.net
        if (!MineTunnel.offlineMode) {
            allow = false;
            try {
                URL verify = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(message.getName(), "UTF-8") + "&serverId=" + URLEncoder.encode(session.getSessionId(), "UTF-8"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(verify.openStream()));
                String result = reader.readLine();
                reader.close();
                allow = result.equals("YES"); // Get minecraft.net's result. If the result is YES, allow login to continue
            } catch (IOException ex) {
                session.disconnect("Player identification failed [" + ex.getMessage() + "]");
            }
        }
        // Was the player allowed?
        if (allow) {
        }
    }
}
