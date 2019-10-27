package com.newtouch.cloud.demo.service.userauthority.controller;

import com.newtouch.cloud.demo.service.userauthority.controller.criteria.UsernameCriteria;
import com.newtouch.cloud.demo.service.userauthority.service.UserAuthorityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserAuthorityController {

    private UserAuthorityService userAuthorityService;

    public UserAuthorityController(UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    // 该方法使用POST，是因为Get只能通过URL Encode形式编码，容易造成数据泄露
    @PostMapping("/by-name")
    public UserDetails getSystemUserByUsername(@Validated UsernameCriteria usernameCriteria) {
        return userAuthorityService.getByUsername(usernameCriteria.getUsername());
    }

}
