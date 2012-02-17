package protocol;

import server.ServerHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;

public class PipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        ServerHandler handler = new ServerHandler();
        Encoder encoder = new Encoder();
        return new StaticChannelPipeline(new Decoder(handler, encoder), encoder, handler);
    }
}
