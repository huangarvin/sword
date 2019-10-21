package com.huangsuip.api.config;

import javax.sql.DataSource;

import com.huangsuip.framework.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
    @Primary
    public DataSource getDataSourceAll(
            @Lazy @Qualifier("writeDatasource") DataSource write,
            @Lazy @Qualifier("readDatasource") DataSource read
    ) {
        //读写分离数据源
        return new DynamicDataSource(write, read);
    }

/*    @Bean(name = "dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("readAndWrite") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

/*    @Bean
    public JtaTransactionManager jtaTransactionManager(TransactionManager transactionManager) {
        return new JtaTransactionManager(transactionManager);
    }*/
}
