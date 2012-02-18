package client;

import minetunnel.Session;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;
import protocol.Decoder;
import protocol.Encoder;

public class ClientPipelineFactory implements ChannelPipelineFactory {

    private final Session session;

    public ClientPipelineFactory(Session session) {
        this.session = session;
    }

    public ChannelPipeline getPipeline() throws Exception {
        return new StaticChannelPipeline(new Decoder(), new Encoder(), new ClientHandler(session));
    }
}
