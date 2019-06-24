package com.huangsuip.api.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangsuip.common.response.ResponseMessage;
import com.huangsuip.framework.util.JSONUtils;
import com.huangsuip.framework.util.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author HuangSuip
 */
public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        try {
            logger.info("ExceptionFilter start");
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof AccessDeniedException) {
                LogUtils.info("没有权限异常", e);
                response.setStatus(HttpStatus.OK.value());
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSONUtils.toJSONString(ResponseMessage.error(101, "没有权限")));
                response.setContentType("application/json");
            } else {
                throw e;
            }
        }
        logger.info("ExceptionFilter end");
    }
}
