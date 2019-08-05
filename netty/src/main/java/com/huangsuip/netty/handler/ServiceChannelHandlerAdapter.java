package com.huangsuip.netty.handler;

import com.alibaba.fastjson.JSON;
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
