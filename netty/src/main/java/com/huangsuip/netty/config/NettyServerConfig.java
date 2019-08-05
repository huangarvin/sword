package com.huangsuip.netty.config;

import javax.annotation.PostConstruct;

import com.huangsuip.netty.handler.ChildChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangSuip
 */
@Configuration
public class NettyServerConfig {

    private Logger logger = LoggerFactory.getLogger(NettyServerConfig.class);

    @Value("${netty.port:8081}")
    private int port;

    @PostConstruct
    public void nettyStart() throws InterruptedException {
        bind(port);
    }

    private void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            logger.info("Netty bootstrap read");
            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            if (channelFuture.isSuccess()) {
                logger.info("Netty bootstrap start");
            }
            // 等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
            logger.info("Netty bootstrap end");
        } finally {
            // 优雅退出，释放线程池资源
            logger.info("Netty bootstrap finally");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyServerConfig config = new NettyServerConfig();
        config.bind(8081);
    }
}
