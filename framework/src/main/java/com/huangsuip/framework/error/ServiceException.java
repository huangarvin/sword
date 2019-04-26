package com.huangsuip.framework.error;

/**
 * @author HuangSuip
 */
public class ServiceException extends RuntimeException {
    private static final int DEFAULT_CODE = -1;

    private final int code;

    public ServiceException() {
        super();
        this.code = DEFAULT_CODE;
    }

    public ServiceException(final String message) {
        super(message);
        this.code = DEFAULT_CODE;
    }

    public ServiceException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(final Throwable cause) {
        this(DEFAULT_CODE, getMessage(cause), cause);
    }

    public ServiceException(final String message, final Throwable cause) {
        this(DEFAULT_CODE, message, cause);
    }

    private static String getMessage(final Throwable cause) {
        if (cause == null) {
            return null;
        }
        return cause.getMessage();
    }

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    public int getCode() {
        return this.code;
    }
}
