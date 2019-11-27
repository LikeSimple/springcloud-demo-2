package com.newtouch.cloud.demo.auth.service;

import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface UserService {

    LoginUser searchLoginUserByName(@NotBlank String username);
}
