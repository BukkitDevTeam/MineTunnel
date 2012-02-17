package client;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;
import protocol.Decoder;
import protocol.Encoder;

public class ClientPipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        return new StaticChannelPipeline(new Decoder(), new Encoder(), new ClientHandler());
    }
}
