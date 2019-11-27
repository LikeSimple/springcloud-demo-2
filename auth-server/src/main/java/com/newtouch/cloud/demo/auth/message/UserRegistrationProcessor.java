package com.newtouch.cloud.demo.auth.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserRegistrationProcessor {

    String USER_REGISTER_INPUT = "user_register";

    @Input(USER_REGISTER_INPUT)
    SubscribableChannel input();

}
