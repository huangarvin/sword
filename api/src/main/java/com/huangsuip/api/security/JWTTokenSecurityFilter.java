package com.huangsuip.api.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangsuip.framework.util.LogUtils;
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
        LogUtils.info("doFilterInternal before: " + SecurityContextHolder.getContext().getAuthentication());

        filterChain.doFilter(request, response);

        LogUtils.info("doFilterInternal after: " + SecurityContextHolder.getContext().getAuthentication());
        request.setAttribute(FILTER_APPLIED, true);
        LogUtils.info(SecurityContextHolder.getContext().getAuthentication());
        LogUtils.info("JWTTokenSecurityFilter doFilterInternal end");
    }
}
