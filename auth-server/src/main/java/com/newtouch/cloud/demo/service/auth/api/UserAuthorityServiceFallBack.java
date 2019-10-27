package com.newtouch.cloud.demo.service.auth.api;

import com.newtouch.cloud.demo.service.auth.api.criteria.UsernameCriteria;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthorityServiceFallBack implements UserAuthorityService {
    @Override
    public UserDetails getSystemUserByUsername(UsernameCriteria usernameCriteria) {
        return null;
    }
}
