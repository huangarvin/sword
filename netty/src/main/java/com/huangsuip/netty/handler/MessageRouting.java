package com.huangsuip.netty.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.routing.BaseController;

/**
 * @author HuangSuip
 */
public class MessageRouting {

    private static final String bean = "bean";
    private static final String authentication = "authentication";

    private static final Map<MessageTypeProto.MessageType, Map<String, Object>> BEAN_MAP = new ConcurrentHashMap<>();

    public static BaseController getBean(MessageTypeProto.MessageType type) {
        return (BaseController) BEAN_MAP.get(type).get(bean);
    }

    public static boolean authentication(MessageTypeProto.MessageType type) {
        return (boolean) BEAN_MAP.get(type).get(authentication);
    }

    public static void putBean(MessageTypeProto.MessageType type, BaseController controller) {
        putBean(type, controller, true);
    }

    public static void putBean(MessageTypeProto.MessageType type, BaseController controller, boolean auth) {
        HashMap<String, Object> beanMap = new HashMap<>();
        beanMap.put(bean, controller);
        beanMap.put(authentication, auth);
        BEAN_MAP.put(type, beanMap);
    }
}
