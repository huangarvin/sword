package com.huangsuip.register.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    private Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info("MyApplicationListener: " + applicationEvent.getClass());
        if (applicationEvent instanceof ApplicationStartedEvent){
            logger.info("ApplicationStartedEvent: Project start success");
        }
    }
}
