package com.huangsuip.netty.routing;

import com.google.protobuf.Any;
import com.huangsuip.netty.annotation.MessageController;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author HuangSuip
 */
@MessageController
public class LoginController extends BaseController<Any> {

    @Override
    protected MessagePackageProto.MessagePackage doBusiness(final ChannelHandlerContext context, final Any mp) {

        return null;
    }
}
