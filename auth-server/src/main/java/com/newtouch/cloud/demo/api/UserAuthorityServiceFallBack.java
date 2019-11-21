package com.newtouch.cloud.demo.api;

import com.newtouch.cloud.demo.api.dto.LoginUser;
import com.newtouch.cloud.demo.api.dto.UsernameCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAuthorityServiceFallBack implements UserAuthorityService {

    @Override
    public LoginUser getSystemUserByUsername(UsernameCriteria usernameCriteria) {
        String logStr = String.format("%s not find.", usernameCriteria.getUsername());
        log.info(logStr);
        throw new UsernameNotFoundException(logStr);
    }
}