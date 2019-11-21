package com.newtouch.cloud.demo.service.userauthority.persistence.mapper;

import com.newtouch.cloud.demo.service.userauthority.persistence.model.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {
    LoginUser getByUsername(String username);
}
