package com.szmengran.cloud.warning.config;


import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/** 
 * @Package com.szmengran.cloud.warning.config 
 * @Description: 数据源配置
 * @date Mar 21, 2019 3:02:49 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
public class DataSourceConfig {
    
    @Bean(initMethod = "init", name = "wirteDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    @Primary
    public DataSource createDatasource() {
        return DruidDataSourceBuilder.create().build();
    }
}
