package com.newtouch.cloud.demo.service.user.controller;

import com.newtouch.cloud.demo.service.user.persistence.model.SystemUser;
import com.newtouch.cloud.demo.service.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public SystemUser getSystemUser(String username) {
        return userService.findByUsername(username);
    }

}
