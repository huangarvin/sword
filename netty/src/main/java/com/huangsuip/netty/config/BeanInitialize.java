package com.huangsuip.netty.config;

import com.huangsuip.netty.annotation.MessageMapping;
import com.huangsuip.netty.handler.MessageRouting;
import com.huangsuip.netty.protobuf.MessageTypeProto;
import com.huangsuip.netty.routing.BaseController;
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
        if (!(bean instanceof BaseController)) {
            return bean;
        }
        Class<?> beanClass = bean.getClass();
        MessageMapping annotation = beanClass.getAnnotation(MessageMapping.class);
        if (annotation != null) {
            MessageTypeProto.MessageType value = annotation.value();
            boolean authentication = annotation.authentication();
            MessageRouting.putBean(value, (BaseController) bean, authentication);
        }
        return bean;
    }
}
