package com.huangsuip.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author HuangSuip
 */

@EnableTransactionManagement
@SpringBootApplication
@EnableAspectJAutoProxy
public class ApiApplication {

    //PropertySourceBootstrapConfiguration
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
