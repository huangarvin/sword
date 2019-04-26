package com.huangsuip.framework.util;

/**
 * @author HuangSuip
 */
public class NPEUtils {

    public static <TValue> TValue notNull(final TValue value) {
        if (value == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        return value;
    }

    public static <TValue> TValue notNull(final TValue value, final String name) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Argument '%1$s' must not be null.", name));
        }
        return value;
    }

    public static <TValue> TValue recordIsNull(final TValue value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Argument '%1$s' must not be null."));
        }
        return value;
    }
}
