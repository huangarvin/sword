package com.huangsuip.api.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangSuip
 */
@Configuration
public class BeanConfig {

    @Bean
    public static Flyway flyway(final DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .cleanOnValidationError(true)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load();
        flyway.migrate();
        return flyway;
    }
}
