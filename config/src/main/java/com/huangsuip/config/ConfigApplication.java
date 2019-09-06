package com.huangsuip.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author HuangSuip
 */

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    //PropertySourceBootstrapConfiguration
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
