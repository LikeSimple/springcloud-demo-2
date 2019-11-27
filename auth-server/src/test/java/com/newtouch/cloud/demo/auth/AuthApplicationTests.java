package com.newtouch.cloud.demo.auth;

import com.newtouch.cloud.demo.auth.persistence.mapper.LoginUserMapper;
import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import com.newtouch.cloud.demo.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@Slf4j
public class AuthApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private UserService userService;

    @Test
    public void passwordEncoder() {
        System.out.println(passwordEncoder.encode("test"));
    }

    @Test
    public void MappersTest() {
        LoginUser loginUser = loginUserMapper.getByUsername("test");
        System.out.println(loginUser.toString());
        Assert.notNull(loginUser, "没有找到登录用户，请检查初始化数据");
    }

    @Test
    public void serviceTest() {
        LoginUser loginUser = userService.searchLoginUserByName("test");
        System.out.println(loginUser.toString());
        Assert.notNull(loginUser, "没有找到登录用户，请检查初始化数据");
    }

}
