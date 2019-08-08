package com.huangsuip.netty.handler;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HuangSuip
 */
public class MessageJsonDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Logger logger = LoggerFactory.getLogger(MessageJsonDecoder.class);


/*
        protected void decode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) throws Exception {
        logger.info("MessageConvertHandler decode");
        final int length = msg.readableBytes();
        if (length == msg.readerIndex()) {
            return;
        }
        final byte[] array = ByteBufUtil.getBytes(msg, msg.readerIndex(), length, false);
        String json = new String(array);
        logger.info(json);
        Map nettyMessage = JSON.parseObject(json, Map.class);
        out.add(nettyMessage);
    }
*/

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) throws Exception {
        logger.info("MessageConvertHandler decode");
        logger.debug("ByteBuf: " + JSON.toJSONString(msg));
        String json = msg.toString(StandardCharsets.UTF_8);
        logger.debug("JSON String: " + json);
       // NettyMessage nettyMessage = JSON.parseObject(json, NettyMessage.class);
        //logger.debug("NettyMessage: " + JSON.toJSONString(nettyMessage));
        //out.add(nettyMessage);
    }
}
