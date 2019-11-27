package com.newtouch.cloud.demo.service.user.service.impl;

import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import com.newtouch.cloud.demo.service.user.message.UserRegistrationMessage;
import com.newtouch.cloud.demo.service.user.message.UserRegistrationProcessor;
import com.newtouch.cloud.demo.service.user.persistence.mapper.SystemUserInfoMapper;
import com.newtouch.cloud.demo.service.user.persistence.model.SystemUserInfo;
import com.newtouch.cloud.demo.service.user.service.UserService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

@Service
@EnableBinding(UserRegistrationProcessor.class)
public class UserServiceImpl implements UserService {

    private static final String USER_REGISTER_ACTION = "USER_REGISTER";

    private UserRegistrationProcessor userRegistrationProcessor;

    private SystemUserInfoMapper userInfoMapper;

    public UserServiceImpl(UserRegistrationProcessor userRegistrationProcessor, SystemUserInfoMapper userInfoMapper) {
        this.userRegistrationProcessor = userRegistrationProcessor;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public void register(@NotNull UserCriteria userCriteria) {

        // Send User Registration Info to Auth Service
        userRegistrationProcessor.output().send(MessageBuilder.withPayload(new UserRegistrationMessage(USER_REGISTER_ACTION, userCriteria)).build());

        // Save User Info
        SystemUserInfo userInfo = new SystemUserInfo(userCriteria);
        Assert.isTrue(1 == userInfoMapper.insert(userInfo), "用户保存失败！ userInfo=" + userInfo.toString());
    }
}
