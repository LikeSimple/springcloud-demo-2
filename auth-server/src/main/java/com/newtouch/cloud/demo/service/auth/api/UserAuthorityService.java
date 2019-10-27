package com.newtouch.cloud.demo.service.auth.api;

import com.newtouch.cloud.demo.service.auth.api.criteria.UsernameCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user-authority", fallback = UserAuthorityServiceFallBack.class)
public interface UserAuthorityService {

    @PostMapping("/by-name")
    UserDetails getSystemUserByUsername(UsernameCriteria usernameCriteria);

}
