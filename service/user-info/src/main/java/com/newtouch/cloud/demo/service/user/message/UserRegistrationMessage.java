package com.newtouch.cloud.demo.service.user.message;

import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import lombok.Data;

@Data
public class UserRegistrationMessage {


    private String actionType;

    private String id;

    private String username;

    private String password;

    public UserRegistrationMessage(String actionType, UserCriteria userCriteria) {

        this.actionType = actionType;

        this.id = userCriteria.getId();

        this.username = userCriteria.getUsername();

        this.password = userCriteria.getPassword();

    }
}
