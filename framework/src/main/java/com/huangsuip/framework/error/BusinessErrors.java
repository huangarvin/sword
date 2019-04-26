package com.huangsuip.framework.error;

/**
 * @author HuangSuip
 */
public enum BusinessErrors {

    LOGIN_ERROR(110, "Login error", "登录无效, 请重新登录!");

    private final int code;
    private final String name;
    private final String message;

    /**
     * @param code    错误代码
     * @param name    错误名称(用于查找文字资源和替代掉整数错误代码)
     * @param message 对错误的描述，可以是用于String.format的格式字符串
     */
    BusinessErrors(final int code, final String name, final String message) {
        this.code = code;
        this.name = name;
        this.message = message;
        UniqueConstraint.check(code, name);
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public ServiceException toException() {
        return new ServiceException(this.code, this.message);
    }

    public ServiceException toException(final Throwable cause) {
        return new ServiceException(this.code, this.message, cause);
    }

    public ServiceException toException(Object... args) {
        return new ServiceException(this.code, String.format(this.message, args));
    }

    public ServiceException toException(final Throwable cause, Object... args) {
        return new ServiceException(this.code, String.format(this.message, args), cause);
    }

}
