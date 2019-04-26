package com.huangsuip.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HuangSuip
 */
public class LogUtils {
    private LogUtils() {
    }

    public static Logger LOG = LoggerFactory.getLogger(LogUtils.class);


    public static void debug(String var1) {
        LOG.debug(var1);
    }

    public static void debug(String var1, Object var2) {
        LOG.debug(var1, var2);
    }

    public static void debug(String var1, Throwable var2) {
        LOG.debug(var1, var2);
    }

    public static void info(String var1) {
        LOG.info(var1);
    }
    public static void info(Object var1) {
        LOG.info(JSONUtils.toJSONString(var1));
    }

    public static void info(String var1, Object var2) {
        LOG.info(var1, var2);
    }

    public static void info(String var1, Throwable var2) {
        LOG.info(var1, var2);
    }

    public static void warn(String var1) {
        LOG.warn(var1);
    }

    public static void warn(String var1, Object var2) {
        LOG.warn(var1, var2);
    }

    public static void warn(String var1, Throwable var2) {
        LOG.warn(var1, var2);
    }

    public static void error(String var1) {
        LOG.error(var1);
    }

    public static void error(String var1, Object var2) {
        LOG.error(var1, var2);
    }

    public static void error(String var1, Throwable var2) {
        LOG.error(var1, var2);
    }

}
