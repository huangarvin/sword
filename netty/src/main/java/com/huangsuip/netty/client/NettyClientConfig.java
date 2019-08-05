package com.huangsuip.netty.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.huangsuip.netty.message.Header;
import com.huangsuip.netty.message.MessageType;
import com.huangsuip.netty.message.NettyConstant;
import com.huangsuip.netty.message.NettyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author HuangSuip
 */
public class NettyClientConfig {

    private Logger logger = LoggerFactory.getLogger(NettyClientConfig.class);
    @Value("${netty.port:8081}")
    private int port = 8081;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


    @PostConstruct
    public void startClient() throws Exception {
        connect(port, NettyConstant.REMOTEIP);
    }


    private void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        // 配置客户端NIO线程组
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(final SocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(new HeartBeatReqHandler());
                    }
                });

        logger.info("Netty connect start");
        // 发起异步连接操作
        b.connect(new InetSocketAddress(host, port)).sync();
        // 当对应的channel关闭的时候，就会返回对应的channel。
        // Returns the ChannelFuture which will be notified when this channel is closed. This method always returns the same future instance.
        //future.channel().closeFuture().sync();
    }

    class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

        private final Log LOG = LogFactory.getLog(HeartBeatReqHandler.class);

        private volatile ScheduledFuture<?> heartBeat;

        private ByteBuf firstMessage;

        ByteBuf createMessage() {
            if (firstMessage != null)
                firstMessage = null;

            String message = buildMessage();
            firstMessage = Unpooled.buffer(message.length());
            for (int i = 0; i < firstMessage.capacity(); i++) {
                firstMessage.writeByte(message.charAt(i));
            }
            return firstMessage;
        }

        String buildMessage() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            message.setBody("This is json body");
            return JSON.toJSONString(message);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            LOG.info("Client receive server heart beat message : ---> ");
            NettyMessage message = (NettyMessage) msg;
            // 握手成功，主动发送心跳消息
            if (message.getHeader() != null
                    && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
                //heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
            } else if (message.getHeader() != null
                    && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
                LOG.info("Client receive server heart beat message : ---> " + message);
            } else {
                ctx.fireChannelRead(msg);
            }
        }

        @Override
        public void channelActive(final ChannelHandlerContext ctx) throws Exception {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
            super.channelActive(ctx);
            ctx.writeAndFlush(createMessage());
        }

        @Override
        public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
            LOG.info("NettyClientConfig handler add : ---> ");
            super.handlerAdded(ctx);
            //heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        }

        private class HeartBeatTask implements Runnable {

            private final ChannelHandlerContext ctx;

            public HeartBeatTask(final ChannelHandlerContext ctx) {
                this.ctx = ctx;
            }

            @Override
            public void run() {
                ByteBuf message = createMessage();
                LOG.info("Client send heart beat messsage to server : ---> " + message.toString() );
                ctx.writeAndFlush(buildMessage());
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            if (heartBeat != null) {
                heartBeat.cancel(true);
                heartBeat = null;
            }
            ctx.fireExceptionCaught(cause);
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClientConfig config = new NettyClientConfig();
        config.startClient();
    }
}
