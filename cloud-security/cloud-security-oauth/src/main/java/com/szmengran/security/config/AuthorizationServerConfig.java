package com.szmengran.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.szmengran.common.orm.DBManager;
import com.szmengran.security.service.UserService;

/**
 * Created by wangyunfei on 2017/6/9.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    
    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }


	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    		try {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService())//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore());
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret("$2a$10$FQAIRsqi3dP9bAzfG..5EukHidVpygnN0snZi3xELK/Ioz.Ja/fQq")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit")
            .and()
            		.withClient("admin")
				.authorizedGrantTypes("client_credentials", "password")
				.authorities("ROLE_CLIENT")
				.scopes("read")
				.secret("$2a$10$FQAIRsqi3dP9bAzfG..5EukHidVpygnN0snZi3xELK/Ioz.Ja/fQq")
            ;
//        clients.jdbc(dataSource);
//        clients.withClientDetails(clientDetailsService)
    }
}
