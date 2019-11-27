package com.newtouch.cloud.demo.auth.message;

import lombok.Data;

@Data
public class UserRegistrationMessage {


    private String actionType;

    private String id;

    private String username;

    private String password;

}
