package com.huangsuip.zuul.filter;

import javax.servlet.http.HttpServletResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        logger.error("ErrorFilter filter type");
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        logger.error("ErrorFilter should filter");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.error("ErrorFilter run");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        //是否转发请求
        ctx.setSendZuulResponse(Boolean.TRUE);
        ctx.setResponseStatusCode(HttpStatus.OK.value());
        ctx.setResponseBody("ErrorFilter error");
        return response;
    }
}
