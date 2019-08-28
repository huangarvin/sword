package com.huangsuip.netty.handler;

import com.alibaba.fastjson.JSON;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.routing.BaseController;
import com.huangsuip.netty.utils.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
@ChannelHandler.Sharable
public class ServiceChannelHandlerAdapter extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(ServiceChannelHandlerAdapter.class);

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        if (msg instanceof MessagePackageProto.MessagePackage) {
            MessagePackageProto.MessagePackage message = (MessagePackageProto.MessagePackage) msg;
            logger.info(message.toString());
            if (message.hasHeader()) {
                MessageTypeProto.MessageType type = message.getHeader().getType();
                BaseController bean = MessageRouting.getBean(type);
                bean.channelRead(ctx, msg);
            }

        } else {
            logger.info("Message not instanceof MessagePackage( class: {} content: {})", msg.getClass(), JSON.toJSON(msg));
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
