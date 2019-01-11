package com.suntak.cloud.haiwd.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @Package com.suntak.cloud.ehr.config
 * @Description: 数据源配置
 * @date 2018年4月9日 下午3:34:56
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@Order(-100)
public class DataSourceConfig {
	
	public static final String DLDB_DATASOURCES = "dldb";
	public static final String SZDB_DATASOURCES = "szdb";
	
	@Bean(initMethod = "init", name = DLDB_DATASOURCES)  
    @ConfigurationProperties(prefix = "spring.datasource.druid.dldb")
    public DataSource readDataSource(){  
        return DruidDataSourceBuilder.create().build();  
    }  
  
    @Bean(initMethod = "init", name = SZDB_DATASOURCES)  
    @ConfigurationProperties(prefix = "spring.datasource.druid.szdb")  
    @Primary
    public DataSource writeDataSource(){  
            return DruidDataSourceBuilder.create().build();  
    } 
}
