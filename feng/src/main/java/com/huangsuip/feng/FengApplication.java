package com.huangsuip.feng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HuangSuip
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.huangsuip.feng.dao")
@EnableSwagger2
public class FengApplication {
    public static void main(String[] args) {
        SpringApplication.run(FengApplication.class, args);
    }
}
