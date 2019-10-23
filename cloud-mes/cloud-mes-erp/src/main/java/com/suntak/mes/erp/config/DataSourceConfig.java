package com.suntak.mes.erp.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DataSourceConfig {

	@Bean(initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	@Primary
	public DataSource getDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
}
