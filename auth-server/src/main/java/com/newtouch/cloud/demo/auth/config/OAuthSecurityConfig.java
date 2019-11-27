package com.newtouch.cloud.demo.auth.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerTokenServicesConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@Import(AuthorizationServerTokenServicesConfiguration.class)
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private DataSource dataSource;
    private final TokenStore tokenStore;
    private final AccessTokenConverter tokenConverter;
    private AuthorizationServerProperties properties;

    public OAuthSecurityConfig(AuthenticationConfiguration configuration, PasswordEncoder passwordEncoder,
                               DataSource datasource,
                               ObjectProvider<TokenStore> tokenStore,
                               ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties) throws Exception {
        this.authenticationManager = configuration.getAuthenticationManager();
        this.passwordEncoder = passwordEncoder;
        this.dataSource = datasource;
        this.tokenStore = tokenStore.getIfAvailable();
        this.tokenConverter = tokenConverter.getIfAvailable();
        this.properties = properties;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//        clients.inMemory().withClient("web_app")// 创建一个客户端 名字是web_app
//                .secret("{bcrypt}$2a$10$pfazORB0N6ffporzArY4NefGMCFJ54MlWt29EboRqjIl1UeRgNVoa")
//                .scopes("DEMO")
//                .authorizedGrantTypes("refresh_token", "password")
//                .authorities("DEMO_READ", "DEMO_WRITE")
//                .accessTokenValiditySeconds(3600);
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess(properties.getTokenKeyAccess())
                .checkTokenAccess(properties.getCheckTokenAccess())
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(tokenConverter)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);// support password flow
    }
}
