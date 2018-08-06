package com.suntak.cloud.recruitment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**")
        .and()
        .authorizeRequests().anyRequest().access("#oauth2.hasScope('write')")
        .antMatchers("/api/**").authenticated();
    }
    
    /**
     * 配置资源的ID，只有有该权限的client_id才能访问
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("recruitment");
    }
}
