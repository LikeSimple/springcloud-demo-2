package com.newtouch.cloud.demo.service.user.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserRegistrationProcessor {

    String USER_REGISTER_OUTPUT = "user_register";

    @Output(USER_REGISTER_OUTPUT)
    MessageChannel output();

}
