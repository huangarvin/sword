package com.huangsuip.netty.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangSuip
 */
@Configuration
public class BeanInitialize implements BeanPostProcessor {

    Logger logger = LoggerFactory.getLogger(BeanInitialize.class);
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        logger.info(beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        logger.info(beanName);
        return bean;
    }
}
