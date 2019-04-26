package com.huangsuip.zuul.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangSuip
 */
@Configuration
@EnableZuulProxy
public class ZuulConfig {

    @Bean
    public IRule iRule(){
        return new RoundRobinRule();                //轮训
        //return new WeightedResponseTimeRule();    //加权权重
        //return new RetryRule();                    //带有重试机制的轮训
        //return new RandomRule();                   //随机
        //return new TestRule();                     //自定义规则
    }
}
