package com.newtouch.cloud.demo.service.user.controller;

import com.newtouch.cloud.demo.common.CommonExceptionAdvice;
import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import com.newtouch.cloud.demo.service.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends CommonExceptionAdvice {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCriteria userCriteria) {
        userService.register(userCriteria);
    }

}
