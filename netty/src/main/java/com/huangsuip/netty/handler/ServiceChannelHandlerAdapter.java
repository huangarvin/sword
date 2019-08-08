package com.huangsuip.netty.handler;

import com.alibaba.fastjson.JSON;
import com.huangsuip.netty.protobuf.HeaderProto;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.utils.ChannelUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HuangSuip
 */
public class ServiceChannelHandlerAdapter extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(ServiceChannelHandlerAdapter.class);

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        logger.debug(JSON.toJSONString(msg));
        if (msg instanceof MessagePackageProto.MessagePackage){
            MessagePackageProto.MessagePackage message = (MessagePackageProto.MessagePackage) msg;
            HeaderProto.Header header = message.getHeader();
            MessageTypeProto.MessageType type = header.getType();
        }
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel read complete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel handler added ");
        ChannelUtils.add(ctx);
    }

    @Override
    public void handlerRemoved(final ChannelHandlerContext ctx) throws Exception {
        logger.debug("channel read complete");
        ChannelUtils.remove(ctx.channel());
    }
}
