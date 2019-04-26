package com.huangsuip.framework.error;

import java.util.HashSet;
import java.util.Set;
import com.huangsuip.framework.util.NPEUtils;

/**
 * @author HuangSuip
 */
public final class UniqueConstraint {

    private UniqueConstraint() {
        throw new UnsupportedOperationException();
    }

    private static final Set<Integer> CODES = new HashSet<>();
    private static final Set<String> NAMES = new HashSet<>();

    public static void check(final Integer code, String name) {

        NPEUtils.notNull(code);
        NPEUtils.notNull(name);
        name = name.toUpperCase();
        if (CODES.contains(code)) {
            throw new UnsupportedOperationException("Duplicated code: " + code);
        }
        if (NAMES.contains(name)) {
            throw new UnsupportedOperationException("Duplicated name: " + name);
        }
        CODES.add(code);
        NAMES.add(name);
    }

}