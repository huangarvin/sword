package com.huangsuip.common.response;

import java.util.List;

/**
 * @author HuangSuip
 */
public class ResponseMessage<T> implements ResponseInterface<T> {

    static final int CODE_OK = 0;
    private static final int UNKNOWN_ERROR = -1;

    private final Integer code;
    private final String message;
    private final T data;

    ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseMessage<T> ok(T date) {
        return new ResponseMessage<>(CODE_OK, null, date);
    }

    public static ResponseMessage ok() {
        return new ResponseMessage<>(CODE_OK, null, "OK");
    }

    public static <E> ResponseMessage<Iterable<E>> ok(Iterable<E> date) {
        return new ResponseMessage<>(CODE_OK, null, date);
    }

    public static <E> ResponseMessage<E[]> ok(final E[] data) {
        return new ResponseMessage<>(CODE_OK, null, data);
    }

    public static <T> ResponseMessage<T> error(String message) {
        return new ResponseMessage<>(UNKNOWN_ERROR, message, null);
    }

    public static ResponseMessage error(final int code, final String message) {
        return new ResponseMessage<>(code, message, null);
    }

    public static <T> PageResponseMessage<T> ok(Integer page, Integer pageSize, Integer total, List<T> data) {
        return new PageResponseMessage<>(page, pageSize, total, data);
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }
}
