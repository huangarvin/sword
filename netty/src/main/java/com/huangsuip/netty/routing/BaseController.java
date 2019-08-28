package com.huangsuip.netty.routing;

import com.google.protobuf.Any;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HuangSuip
 */
public abstract class BaseController<T> extends ChannelInboundHandlerAdapter {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
        if (!(msg instanceof MessagePackageProto.MessagePackage)) {
            return;
        }
        MessagePackageProto.MessagePackage mp = (MessagePackageProto.MessagePackage) msg;
        if (!mp.hasBody()) {
            return;
        }
        Any body = mp.getBody();
        T t = (T) body;
        MessagePackageProto.MessagePackage messagePackage = doBusiness(ctx, t);
        if (messagePackage != null && messagePackage.hasHeader()) {
            logger.info("Response message: {}", messagePackage.toString());
            if (MessageTypeProto.MessageType.ONE_WAY.equals(messagePackage.getHeader().getType())
                    || MessageTypeProto.MessageType.UNRECOGNIZED.equals(messagePackage.getHeader().getType())) {
                return;
            }
            ctx.writeAndFlush(messagePackage);
        }
    }

    protected abstract MessagePackageProto.MessagePackage doBusiness(ChannelHandlerContext context, T mp);
}
