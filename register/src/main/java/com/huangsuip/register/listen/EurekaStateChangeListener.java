package com.huangsuip.register.listen;

import com.huangsuip.framework.util.LogUtils;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        LogUtils.info(event.getServerId() + " " + event.getAppName() + " 服务下线");
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        LogUtils.info(instanceInfo.getAppName() + "进行注册");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        LogUtils.info(event.getServerId() + "  " + event.getAppName() + " 服务进行续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        LogUtils.info("注册中心 启动");


    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {

        LogUtils.info("Eureka Server 启动");
    }

}

