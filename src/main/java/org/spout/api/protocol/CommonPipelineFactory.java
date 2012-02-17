package org.spout.api.protocol;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.StaticChannelPipeline;

public class CommonPipelineFactory implements ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        CommonHandler handler = new CommonHandler();
        CommonEncoder encoder = new CommonEncoder();
        return new StaticChannelPipeline(new CommonDecoder(handler, encoder), encoder, handler);
    }
}
