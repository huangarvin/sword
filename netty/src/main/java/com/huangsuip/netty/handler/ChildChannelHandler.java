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

/**
 * @author HuangSuip
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

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
        pipeline.addLast(new ServiceChannelHandlerAdapter());
    }
}