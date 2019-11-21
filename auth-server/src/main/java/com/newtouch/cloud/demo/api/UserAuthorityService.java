package com.newtouch.cloud.demo.api;

import com.newtouch.cloud.demo.api.dto.LoginUser;
import com.newtouch.cloud.demo.config.FeignClientConfig;
import com.newtouch.cloud.demo.api.dto.UsernameCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-authority", configuration = FeignClientConfig.class, fallback = UserAuthorityServiceFallBack.class)
public interface UserAuthorityService {

    @RequestMapping(value = "/by-name", method = RequestMethod.POST)
    LoginUser getSystemUserByUsername(@RequestBody UsernameCriteria usernameCriteria);

}
