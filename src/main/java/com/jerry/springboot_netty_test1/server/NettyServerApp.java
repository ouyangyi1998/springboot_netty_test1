package com.jerry.springboot_netty_test1.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NettyServerApp implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) throws Exception{
        ApplicationContext context= SpringApplication.run(NettyServerApp.class,args);
        NettyServer nettyServer=context.getBean(NettyServer.class);
        nettyServer.run();
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8081);
    }
}
