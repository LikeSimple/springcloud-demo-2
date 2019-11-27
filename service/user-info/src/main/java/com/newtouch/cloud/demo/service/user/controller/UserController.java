package com.newtouch.cloud.demo.service.user.controller;

import com.newtouch.cloud.demo.common.CommonExceptionAdvice;
import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import com.newtouch.cloud.demo.service.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PreAuthorize("hasAuthority('AUTH_ADMIN')")
    @GetMapping("/whoami")
    public String whoAmI(@AuthenticationPrincipal String name) {
        return name;
    }
}
