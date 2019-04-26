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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author HuangSuip
 */
//@Component
public class JWTTokenSecurityFilter extends OncePerRequestFilter {

    private final String FILTER_APPLIED = "spring_security_JWTTokenSecurityFilter";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        LogUtils.info("JWTTokenSecurityFilter doFilterInternal start");
        //通过打标识避免执行两次  或者bean 不交给spring管理
        if (request.getAttribute(FILTER_APPLIED) != null) {
            filterChain.doFilter(request, response);
            return;
        }

        LogUtils.info(SecurityContextHolder.getContext().getAuthentication());
        request.setAttribute(FILTER_APPLIED, true);
        try {
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
        LogUtils.info(SecurityContextHolder.getContext().getAuthentication());
        LogUtils.info("JWTTokenSecurityFilter doFilterInternal end");
    }
}
