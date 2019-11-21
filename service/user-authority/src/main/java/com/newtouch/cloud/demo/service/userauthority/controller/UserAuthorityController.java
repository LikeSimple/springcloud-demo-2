package com.newtouch.cloud.demo.service.userauthority.controller;

import com.newtouch.cloud.demo.service.userauthority.controller.criteria.UsernameCriteria;
import com.newtouch.cloud.demo.service.userauthority.persistence.model.LoginUser;
import com.newtouch.cloud.demo.service.userauthority.service.UserAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserAuthorityController {

    private UserAuthorityService userAuthorityService;

    public UserAuthorityController( UserAuthorityService userAuthorityService) {
        this.userAuthorityService = userAuthorityService;
    }

    // 该方法使用POST，是因为Get只能通过URL Encode形式编码，容易造成数据泄露
    @PostMapping("by-name")
    public LoginUser getSystemUserByUsername(@RequestBody UsernameCriteria usernameCriteria) {
        Assert.notNull(usernameCriteria, "用户名查询对象为空");
        Assert.notNull(usernameCriteria.getUsername(), "用户名参数为空");
        log.info("用户：" + usernameCriteria.getUsername() + "申请登录！");
        return userAuthorityService.getByUsername(usernameCriteria.getUsername());
    }

    // 打印当前登录用户名
    @PreAuthorize("hasAuthority('AUTH_ADMIN')")
    @GetMapping("/whoami")
    public String whoami() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

