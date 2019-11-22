package com.jerry.springboot_netty_test1.client;

import com.alibaba.fastjson.parser.ParserConfig;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyClientHandler nettyClientHandler;
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph=socketChannel.pipeline();
        ph.addLast("decoder",new StringDecoder());
        ph.addLast("encoder",new StringEncoder());
        ph.addLast("handler",nettyClientHandler);
    }
}
