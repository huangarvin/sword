package com.huangsuip.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class JWTFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Override
    public String filterType() {
        logger.info("JWTFilter filterType into");
        return "pre";
    }

    @Override
    public int filterOrder() {
        logger.info("JWTFilter filterOrder into");
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否要执行过滤器 (执行run方法)
        logger.info("JWTFilter shouldFilter into");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("JWTFilter run into");
        //设置随机数
        if (Math.random() > 0.9){
            //throw new RuntimeException();
        }
        //RequestContext ctx = RequestContext.getCurrentContext();
        //ctx.setResponseStatusCode(HttpStatus.SEE_OTHER.value());
        return "JWTFilter run into";
    }
}
