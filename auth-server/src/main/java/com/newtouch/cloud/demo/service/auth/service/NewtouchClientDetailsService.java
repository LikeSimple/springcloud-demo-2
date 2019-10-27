package com.newtouch.cloud.demo.service.auth.service;

import com.newtouch.cloud.demo.service.auth.persistence.mapper.OauthClientDetailsMapper;
import com.newtouch.cloud.demo.service.auth.persistence.model.LoginOAuthClientDetails;
import com.newtouch.cloud.demo.service.auth.persistence.model.OauthClientDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.util.Assert;

@Configuration
public class NewtouchClientDetailsService implements ClientDetailsService {

    private OauthClientDetailsMapper oauthClientDetailsMapper;

    public NewtouchClientDetailsService (OauthClientDetailsMapper oauthClientDetailsMapper) {
        this.oauthClientDetailsMapper = oauthClientDetailsMapper;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByPrimaryKey(clientId);
        Assert.notNull(oauthClientDetails, String.format("%s 指定客户未找到！", clientId));
        return new LoginOAuthClientDetails(oauthClientDetails);
    }

    @Bean
    ClientDetailsService getClientDetailsService(OauthClientDetailsMapper oauthClientDetailsMapper) {
        return new NewtouchClientDetailsService(oauthClientDetailsMapper);
    }
}
