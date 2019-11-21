package com.newtouch.cloud.demo.config;

import com.newtouch.cloud.demo.api.UserAuthorityService;
import com.newtouch.cloud.demo.api.dto.LoginUser;
import com.newtouch.cloud.demo.api.dto.UsernameCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

@Configuration
@Slf4j
public class NewtouchUserDetailsService implements UserDetailsService {

    private UserAuthorityService userAuthorityService;

    public NewtouchUserDetailsService(UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "申请登录用户名为空！");
        log.info("申请登录用户：" + username);
        LoginUser loginUser = userAuthorityService.getSystemUserByUsername(new UsernameCriteria(username));
        log.info("获得登录用户信息" + loginUser.toString());
        return loginUser;
    }

    @Bean
    public NewtouchUserDetailsService getUserDetailsService(UserAuthorityService userAuthorityService) {
        return new NewtouchUserDetailsService(userAuthorityService);
    }
}
