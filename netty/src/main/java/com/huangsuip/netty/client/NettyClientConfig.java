package com.huangsuip.netty.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

import com.huangsuip.netty.protobuf.HeaderProto;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
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
        connect(port, "localhost");
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
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ProtobufVarint32FrameDecoder());
                        pipeline.addLast(new ProtobufDecoder(MessagePackageProto.MessagePackage.getDefaultInstance()));
                        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                        pipeline.addLast(new ProtobufEncoder());
                        pipeline.addLast(new HeartBeatReqHandler());
                    }
                });

        logger.info("Netty connect start");
        // 发起异步连接操作
        b.connect(new InetSocketAddress(host, port)).sync();
        // 当对应的channel关闭的时候，就会返回对应的channel。
        // Returns the ChannelFuture which will be notified when this channel is closed. This method always returns the same future instance.
        //future.channel().closeFuture().sync();
        logger.info("Netty connect end");
    }

    class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

        private final Log LOG = LogFactory.getLog(HeartBeatReqHandler.class);

        private volatile ScheduledFuture<?> heartBeat;


        MessagePackageProto.MessagePackage buildMessage() {
            HeaderProto.Header header = HeaderProto.Header.newBuilder()
                    .setType(MessageTypeProto.MessageType.HEARTBEAT_REQ)
                    .build();
            return MessagePackageProto.MessagePackage.newBuilder()
                    .setHeader(header).build();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

/*            LOG.info("Client receive server heart beat message : ---> ");
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
            }*/
            //heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        }

        @Override
        public void channelActive(final ChannelHandlerContext ctx) throws Exception {
            logger.info("channelActive");
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 1000, 5000, TimeUnit.MILLISECONDS);
            super.channelActive(ctx);
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
                MessagePackageProto.MessagePackage messagePackage = buildMessage();
                LOG.info("Client send heart beat messsage to server : ---> " + messagePackage.toString());
                ctx.writeAndFlush(messagePackage);
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
