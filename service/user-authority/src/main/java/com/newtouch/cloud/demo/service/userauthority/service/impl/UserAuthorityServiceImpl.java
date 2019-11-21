package com.newtouch.cloud.demo.service.userauthority.service.impl;

import com.github.pagehelper.PageHelper;
import com.newtouch.cloud.demo.service.userauthority.controller.criteria.SystemUserCriteria;
import com.newtouch.cloud.demo.service.userauthority.persistence.mapper.LoginUserMapper;
import com.newtouch.cloud.demo.service.userauthority.persistence.mapper.SystemUserMapper;
import com.newtouch.cloud.demo.service.userauthority.persistence.model.LoginUser;
import com.newtouch.cloud.demo.service.userauthority.persistence.model.SystemUser;
import com.newtouch.cloud.demo.service.userauthority.service.UserAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAuthorityServiceImpl implements UserAuthorityService {

    private LoginUserMapper loginUserMapper;

    private SystemUserMapper systemUserMapper;

    public UserAuthorityServiceImpl (LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public LoginUser getByUsername(String username) {
        LoginUser loginUser = loginUserMapper.getByUsername(username);
        if (null == loginUser) log.info("没有找到用户：" + username + "！");
        else log.info("找到用户信息：" + loginUser.toString());
        return loginUser;
    }

    @Override
    public List<SystemUser> getSystemUsers(SystemUserCriteria systemUserCriteria, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize, systemUserCriteria.getOrderBy());
        return systemUserMapper.selectAll();
    }

    @Override
    public SystemUser getSystemUser(String id) {
        return systemUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void resetPassword(String id, String oldPassword, String newPassword) {

    }

    @Override
    public void newSystemUser(SystemUser systemUser) {

    }


}
