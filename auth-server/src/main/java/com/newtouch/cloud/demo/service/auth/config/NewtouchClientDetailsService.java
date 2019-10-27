package com.newtouch.cloud.demo.service.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

@Configuration
public class NewtouchClientDetailsService implements ClientDetailsService {

    public NewtouchClientDetailsService() {

    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new NewtouchClientDetailsService();
    }
}

