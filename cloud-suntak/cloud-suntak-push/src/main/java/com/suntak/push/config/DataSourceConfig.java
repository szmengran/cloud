package com.suntak.push.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 
 * @description 数据源配置
 * @package com.suntak.push.config 
 * @date Sep 27, 2019 1:21:50 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
public class DataSourceConfig {
  
    @Bean(initMethod = "init", name = "writeDataSource")  
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")  
    @Primary
    public DataSource writeDataSource(){  
            return DruidDataSourceBuilder.create().build();  
    } 
}
