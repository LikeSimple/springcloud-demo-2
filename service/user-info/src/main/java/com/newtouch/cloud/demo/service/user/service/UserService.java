package com.newtouch.cloud.demo.service.user.service;

import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface UserService {

    void register(@NotNull UserCriteria userCriteria);

}
