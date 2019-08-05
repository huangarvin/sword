package com.huangsuip.api.config;

import javax.sql.DataSource;

import com.huangsuip.framework.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author HuangSuip
 */
@Configuration
public class DatasourceConfig {


    @Bean("masterProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("writeDatasource")
    @Primary
    public DataSource getDataSourceWrite(@Qualifier("masterProperties") DataSourceProperties properties) {
        DataSource build = properties.initializeDataSourceBuilder().build();
        return build;
    }

    @Bean("slaveProperties")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSourceProperties dataSourceProperties2() {
        return new DataSourceProperties();
    }

    @Bean("readDatasource")
    public DataSource getDataSourceRead(@Qualifier("slaveProperties") DataSourceProperties properties) {
        DataSource build = properties.initializeDataSourceBuilder().build();
        return build;
    }


    @Bean(name = "readAndWrite")
    public DataSource getDataSourceAll(
            @Qualifier("writeDatasource") DataSource write,
            @Qualifier("readDatasource") DataSource read
    ) {
        //读写分离数据源
        return new DynamicDataSource(write, read);
    }
}
