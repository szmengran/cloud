package com.suntak.cloud.haiwd.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @Package com.suntak.cloud.ehr.config
 * @Description: 数据源配置
 * @date 2018年4月9日 下午3:34:56
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Configuration
@Order(-100)
@MapperScan(basePackages = "com.suntak.cloud.haiwd.mapper.sz", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSourceConfig {
	
	public static final String SZDB_DATASOURCES = "szdb";

	@Bean(initMethod = "init", name = SZDB_DATASOURCES)  
    @ConfigurationProperties(prefix = "spring.datasource.druid.szdb")  
    @Primary
    public DataSource writeDataSource(){  
            return DruidDataSourceBuilder.create().build();  
    } 
	
    @Bean(name = "dataSource2TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(SZDB_DATASOURCES) DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
    
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier(SZDB_DATASOURCES) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    
    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
