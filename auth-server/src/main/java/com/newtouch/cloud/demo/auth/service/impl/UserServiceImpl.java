package com.newtouch.cloud.demo.auth.service.impl;

import com.newtouch.cloud.demo.auth.message.UserRegistrationMessage;
import com.newtouch.cloud.demo.auth.message.UserRegistrationProcessor;
import com.newtouch.cloud.demo.auth.persistence.mapper.LoginUserMapper;
import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import com.newtouch.cloud.demo.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(UserRegistrationProcessor.class)
@Slf4j
public class UserServiceImpl implements UserService {

    private LoginUserMapper loginUserMapper;

    public UserServiceImpl(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public LoginUser searchLoginUserByName(String username) {
        return loginUserMapper.getByUsername(username);
    }

    @StreamListener(UserRegistrationProcessor.USER_REGISTER_INPUT)
    public void onUserRegister(UserRegistrationMessage userRegistrationMessage) {
        log.info(String.format("收到用户注册信息：%s", userRegistrationMessage.toString()));
    }
}
