package com.huangsuip.api.config;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.huangsuip.framework.datasource.DynamicPlugin;
import com.huangsuip.framework.util.LogUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author HuangSuip
 */
@Configuration
@AutoConfigureAfter(DatasourceConfig.class)
public class MybatisConfig {

    @Lazy
    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(
            @Qualifier("readAndWrite") DataSource readAndWrite
    ) throws Exception {
        LogUtils.info("Sql session factory init start");
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(readAndWrite);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources("classpath*:/com/huangsuip/mapper/*.xml"));
        //bean.setTypeAliasesPackage("com.huangsuip.service");

        PaginationInterceptor pageInterceptor = new PaginationInterceptor();
        DynamicPlugin dynamicPlugin = new DynamicPlugin();
        bean.setPlugins(new Interceptor[]{pageInterceptor, dynamicPlugin});

        MybatisConfiguration configuration = new MybatisConfiguration();
        //驼峰命名问题
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Lazy
    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Lazy
    @Primary
    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        LogUtils.info("Mapper scanner configurer init start");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.huangsuip.service.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        return mapperScannerConfigurer;
    }
}
/*
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>5.0.0</version>
            </dependency>
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        //分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("pageSizeZero", "true");
        //页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("reasonable", "true");
        //支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        pageInterceptor.setProperties(properties);
*/