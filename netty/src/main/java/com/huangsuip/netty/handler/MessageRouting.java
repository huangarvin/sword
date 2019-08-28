package com.huangsuip.netty.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.routing.BaseController;

/**
 * @author HuangSuip
 */
public class MessageRouting {

    private static final Map<MessageTypeProto.MessageType, BaseController> BEAN_MAP = new ConcurrentHashMap<>();

    public static BaseController getBean(MessageTypeProto.MessageType type) {
        return BEAN_MAP.get(type);
    }

    public static void putBean(MessageTypeProto.MessageType type, Object controller) {
        BEAN_MAP.put(type, (BaseController) controller);
    }
}
