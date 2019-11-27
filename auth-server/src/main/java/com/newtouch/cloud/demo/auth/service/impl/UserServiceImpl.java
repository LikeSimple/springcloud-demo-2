package com.newtouch.cloud.demo.auth.service.impl;

import com.newtouch.cloud.demo.auth.message.UserRegistrationMessage;
import com.newtouch.cloud.demo.auth.message.UserRegistrationProcessor;
import com.newtouch.cloud.demo.auth.persistence.mapper.LoginUserMapper;
import com.newtouch.cloud.demo.auth.persistence.mapper.SystemUserMapper;
import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import com.newtouch.cloud.demo.auth.persistence.model.SystemUser;
import com.newtouch.cloud.demo.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@EnableBinding(UserRegistrationProcessor.class)
@Slf4j
public class UserServiceImpl implements UserService {

    private LoginUserMapper loginUserMapper;

    private SystemUserMapper systemUserMapper;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            LoginUserMapper loginUserMapper,
            SystemUserMapper systemUserMapper,
            PasswordEncoder passwordEncoder) {
        this.loginUserMapper = loginUserMapper;
        this.systemUserMapper = systemUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginUser searchLoginUserByName(String username) {
        return loginUserMapper.getByUsername(username);
    }

    @StreamListener(UserRegistrationProcessor.USER_REGISTER_INPUT)
    public void onUserRegister(UserRegistrationMessage userRegistrationMessage) {
        log.info(String.format("收到用户注册信息：%s", userRegistrationMessage.toString()));

        if ("register".equals(userRegistrationMessage.getActionType())) {
            SystemUser systemUser = new SystemUser(userRegistrationMessage, passwordEncoder);
            Assert.isTrue(1 == systemUserMapper.insert(systemUser), "用户登录信息保存失败");
        } else {
            log.error("未知消息:", userRegistrationMessage.toString());
        }

    }
}
