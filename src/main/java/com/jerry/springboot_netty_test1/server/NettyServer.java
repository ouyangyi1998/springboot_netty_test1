package com.jerry.springboot_netty_test1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {
    private static final int port=6789;
    private static EventLoopGroup group=new NioEventLoopGroup();
    private static ServerBootstrap b=new ServerBootstrap();

    @Autowired
    private NettyServerFilter nettyServerFilter;

    public void run()throws Exception
    {
        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(nettyServerFilter);
            ChannelFuture f=b.bind(port).sync();
            System.out.println("服务器启动成功，端口号是："+port);
            f.channel().closeFuture().sync();
        }catch (Exception e)
        {
            e.getMessage();
        }finally {
            group.shutdownGracefully();
        }
    }
}
