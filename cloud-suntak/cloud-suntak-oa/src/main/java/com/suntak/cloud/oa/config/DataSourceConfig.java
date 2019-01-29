package com.suntak.cloud.oa.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @Package com.suntak.cloud.ehr.config
 * @Description: 数据源配置
 * @date 2018年4月9日 下午3:34:56
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
