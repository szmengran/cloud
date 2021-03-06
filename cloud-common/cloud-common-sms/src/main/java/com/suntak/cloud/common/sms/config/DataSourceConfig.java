package com.suntak.cloud.common.sms.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @Package com.szmengran.cloud.common.sms.config
 * @Description: 数据源配置
 * @date 2018年4月9日 下午3:34:56
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@Order(-100)
public class DataSourceConfig {
	
	@Bean(initMethod = "init", name = "readDataSource")  
    @ConfigurationProperties(prefix = "spring.datasource.druid.read")  
    public DataSource readDataSource(){  
        return DruidDataSourceBuilder.create().build();  
    }  
  
    @Bean(initMethod = "init", name = "writeDataSource")  
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")  
    public DataSource writeDataSource(){  
            return DruidDataSourceBuilder.create().build();  
    } 
}
