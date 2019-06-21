package com.newtouch.cloud.demo.service.user.service.impl;

import com.newtouch.cloud.demo.service.user.persistence.mapper.SystemUserMapper;
import com.newtouch.cloud.demo.service.user.persistence.model.SystemUser;
import com.newtouch.cloud.demo.service.user.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private SystemUserMapper systemUserMapper;

    @Override
    public SystemUser findByUsername(String username) {
        return systemUserMapper.selectByUsername(username);
    }
}
