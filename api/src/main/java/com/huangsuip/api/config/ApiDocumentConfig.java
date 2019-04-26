package com.huangsuip.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HuangSuip
 */
@Configuration
@EnableSwagger2
public class ApiDocumentConfig {

    @Bean
    public static Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huangsuip.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private static ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("HunagSuip Sword Web API")
                .termsOfServiceUrl("http://localhost:8027")
                .version("1.0")
                .build();
    }
}
