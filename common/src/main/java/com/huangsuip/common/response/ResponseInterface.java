package com.huangsuip.common.response;

/**
 * @author HuangSuip
 */
public interface ResponseInterface<T> {

    Integer getCode();

    String getMessage();

    T getData();

}
