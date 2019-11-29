package com.suntak.report.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DataSourceConfig {

	@ConfigurationProperties("spring.datasource.druid")
	@Primary
	@Bean(initMethod = "init", name = "writeDataSource")
	public DataSource getWriteDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@ConfigurationProperties("spring.datasource.druid")
	@Bean(initMethod = "init", name = "readDataSource")
	public DataSource getReadDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
}
