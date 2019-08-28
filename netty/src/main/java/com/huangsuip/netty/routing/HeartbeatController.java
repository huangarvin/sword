package com.huangsuip.netty.routing;

import com.google.protobuf.Any;
import com.huangsuip.netty.annotation.MessageMapping;
import com.huangsuip.netty.protobuf.HeaderProto;
import com.huangsuip.netty.protobuf.MessagePackageProto;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.protobuf.PersonProto;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author HuangSuip
 */
@MessageMapping(MessageTypeProto.MessageType.HEARTBEAT_REQ)
public class HeartbeatController extends BaseController<Any> {

    @Override
    protected MessagePackageProto.MessagePackage doBusiness(final ChannelHandlerContext context, final Any mp) {
        HeaderProto.Header header = HeaderProto.Header.newBuilder()
                .setType(MessageTypeProto.MessageType.HEARTBEAT_RESP)
                .build();
        PersonProto.Person person = PersonProto.Person.newBuilder().setId(1).build();
        return MessagePackageProto.MessagePackage.newBuilder()
                .setHeader(header)
                .setBody(Any.newBuilder().setValue(person.toByteString()))
                .build();
    }
}
