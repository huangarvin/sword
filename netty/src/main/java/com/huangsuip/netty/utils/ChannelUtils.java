package com.huangsuip.netty.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

/**
 * @author HuangSuip
 */
public final class ChannelUtils {

    private ChannelUtils() { }
    public static AttributeKey<SecurityProperties.User> SESSION_KEY = AttributeKey.valueOf("session");
    private static final ChannelGroup ALL_GROUP = new DefaultChannelGroup("ALL_GROUP", GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<Long, Channel> allChannel = new ConcurrentHashMap<>();

    public static boolean add(ChannelHandlerContext context) {
        return add(context.channel());
    }

    public static boolean add(Channel channel) {
        if (channel == null){
            return false;
        }
        return ALL_GROUP.add(channel);
    }

    public static boolean remove(Channel channel) {
        if (channel == null){
            return false;
        }
        return ALL_GROUP.remove(channel);
    }

    public static Channel find(ChannelId id){
        return ALL_GROUP.find(id);
    }
}
