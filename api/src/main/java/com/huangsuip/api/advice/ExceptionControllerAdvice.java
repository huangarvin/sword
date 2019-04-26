package com.huangsuip.api.advice;

import java.util.LinkedHashMap;
import java.util.Map;
import com.google.common.base.Throwables;
import com.huangsuip.framework.error.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author HuangSuip
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    private static final String GENERIC_ERROR_MESSAGE = "服务端发生未知错误";
    private boolean errorOutput = true;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public Object handleException(final ServiceException e) {
        LOGGER.error(e.getMessage(), e);
        return this.formatException(e);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Object handleException(final Exception e) {
        LOGGER.error(e.getMessage(), e);
        return this.formatException(e);
    }

    private Map formatException(final ServiceException e) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("code", e.getCode());
        data.put("message", e.getMessage());
        if (errorOutput){
            this.appendException(data, e);
        }
        return data;
    }

    private Map formatException(final Exception e) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("code", -1);
        data.put("message", GENERIC_ERROR_MESSAGE);
        if (errorOutput){
            this.appendException(data, e);
        }
        return data;
    }

    private void appendException(final Map<String, Object> node, final Throwable e) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("message", e.getMessage());
        data.put("class", e.getClass().getName());
        data.put("stackTrace", Throwables.getStackTraceAsString(e));
        if (e.getCause() != null) {
            this.appendException(data, e.getCause());
        }
        node.put("exception", data);
    }

}
