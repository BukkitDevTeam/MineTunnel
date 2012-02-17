package org.spout.api.protocol;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;

public class PipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        Handler handler = new Handler();
        Encoder encoder = new Encoder();
        return new StaticChannelPipeline(new Decoder(handler, encoder), encoder, handler);
    }
}
