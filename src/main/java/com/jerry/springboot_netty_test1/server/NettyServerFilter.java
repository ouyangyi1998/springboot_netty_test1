package com.jerry.springboot_netty_test1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
    @Autowired
    private NettyServerHandler nettyServerHandler;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph=socketChannel.pipeline();
        //ph.addLast("aggregator",new HttpObjectAggregator(10*1024*1024));
        ph.addLast(new LineBasedFrameDecoder(4000));
        ph.addLast("decoder",new StringDecoder());
        ph.addLast("encoder",new StringEncoder());
        ph.addLast("handler",nettyServerHandler);
    }
}
