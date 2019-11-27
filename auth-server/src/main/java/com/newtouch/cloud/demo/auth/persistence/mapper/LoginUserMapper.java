package com.newtouch.cloud.demo.auth.persistence.mapper;

import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {
    LoginUser getByUsername(String username);
}
