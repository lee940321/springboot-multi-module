package com.lee.star.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * 德鲁伊数据源配置类
 */

@Configuration
public class DataSourceConfiguration {
    Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean(name = "defaultDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource defaultDataSource() throws SQLException {
        logger.info("---------德鲁伊数据源开始设置监控--------");
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        logger.info("---------德鲁伊数据源配置成功--------");
        return druidDataSource;
    }

}
