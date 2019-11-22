package com.jerry.springboot_netty_test1.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private int count=0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body=(String) msg;
        count++;
        System.out.println("接受的数据是："+body+";条数是:"+count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:"+ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端"+ InetAddress.getLocalHost().getHostName()+"成功与服务器建立连接");
        super.channelActive(ctx);
    }
}
