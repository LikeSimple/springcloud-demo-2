package com.newtouch.cloud.demo.service.user.service;

import com.newtouch.cloud.demo.service.user.persistence.model.SystemUser;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface UserService {

    SystemUser findByUsername(@NotNull String username);

}
