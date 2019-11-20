package com.newtouch.cloud.demo.service.auth.config;

import com.newtouch.cloud.demo.service.auth.api.UserAuthorityService;
import com.newtouch.cloud.demo.service.auth.api.criteria.UsernameCriteria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class NewtouchUserDetailsService implements UserDetailsService {

    private UserAuthorityService userAuthorityService;

    public NewtouchUserDetailsService(UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthorityService.getSystemUserByUsername(new UsernameCriteria(username));
    }

    @Bean
    public NewtouchUserDetailsService getUserDetailsService(UserAuthorityService userAuthorityService) {
        return new NewtouchUserDetailsService(userAuthorityService);
    }
}
