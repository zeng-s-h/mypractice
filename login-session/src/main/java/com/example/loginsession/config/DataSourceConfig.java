package com.example.loginsession.config;

import com.example.loginsession.dynamicdatasource.DynamicDataSource;
import com.example.loginsession.dynamicdatasource.DynamicDataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 小白i
 * @date 2020/7/24
 */
@Configuration
@MapperScan(basePackages = "com.example.loginsession.mapper",sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {

    /**
     * 主数据库
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDb(){
        return new HikariDataSource();
    }

    /**
     * 主数据库
     * @return DataSource
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @ConditionalOnProperty(prefix = "spring.datasource",name = "slave",matchIfMissing = true)
    public DataSource slaveDb(){
        return new HikariDataSource();
    }

    /**
     * 主从动态配置
     */
    @Bean
    public DynamicDataSource dynamicDb(@Qualifier("masterDb") DataSource masterDataSource,
                                       @Autowired(required = false) @Qualifier("slaveDb") DataSource slaveDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
        if (slaveDataSource != null) {
            targetDataSources.put(DynamicDataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }
    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDb") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/**/*.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTx(@Qualifier("dynamicDb") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}
