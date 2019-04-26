package com.huangsuip.api.config;

import javax.sql.DataSource;

import com.huangsuip.framework.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author HuangSuip
 */
@Configuration
public class DatasourceConfig {


    @Bean("writeDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSourceWrite() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    @Bean("readDatasource")
    @ConfigurationProperties(prefix = "spring.slave-datasource")
    public DataSource getDataSourceRead() {
        DataSource build = DataSourceBuilder.create().build();
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
