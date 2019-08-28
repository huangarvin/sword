package com.huangsuip.netty.handler;

import com.huangsuip.netty.protobuf.MessagePackageProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class SwordChannelHandler extends ChannelInitializer<SocketChannel> {

    private final ServiceChannelHandlerAdapter serviceChannelHandlerAdapter;

    public SwordChannelHandler(final ServiceChannelHandlerAdapter serviceChannelHandlerAdapter) {this.serviceChannelHandlerAdapter = serviceChannelHandlerAdapter;}

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        //socketChannel.pipeline().addLast(new JsonObjectDecoder());
        //socketChannel.pipeline().addLast(new StringDecoder());
        ChannelPipeline pipeline = socketChannel.pipeline();
        //MessagePackageProto.MessagePackage build = MessagePackageProto.MessagePackage.newBuilder().build();
        //MessagePackageProto.MessagePackage defaultInstance = MessagePackageProto.MessagePackage.getDefaultInstance();

        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(MessagePackageProto.MessagePackage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(60));
        pipeline.addLast(serviceChannelHandlerAdapter);
    }
}