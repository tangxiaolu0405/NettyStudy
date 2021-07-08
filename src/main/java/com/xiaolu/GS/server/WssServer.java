package com.xiaolu.GS.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class WssServer {

    /**
     * 单例静态内部类
     */
    public static class SingletonWSServer {
        static final WssServer instance = new WssServer();
    }

    public static WssServer getInstance() {
        return SingletonWSServer.instance;
    }

    private static final EventLoopGroup mainGroup = new NioEventLoopGroup();
    private static final EventLoopGroup subGroup = new NioEventLoopGroup();

    private Channel channel;

    public ChannelFuture  start(String hostname,int port) throws Exception {

        ChannelFuture f = null;
        try {
            //ServerBootstrap负责初始化netty服务器，并且开始监听端口的socket请求
            ServerBootstrap b = new ServerBootstrap();
            b.group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(hostname,port))
                    .childHandler(new WssServerInitializer());

            f = b.bind().sync();
            channel = f.channel();
            log.info("======Server启动成功!!!=========");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (f != null && f.isSuccess()) {
                log.info("Netty server listening " + hostname + " on port " + port + " and ready for connections...");
            } else {
                log.error("Netty server start up Error!");
            }
        }
        return f;
    }

    /**
     * 停止服务
     */
    public void destroy() {
        log.info("Shutdown Netty Server...");
        if(channel != null) { channel.close();}
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }

}