package com.newtouch.cloud.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AuthApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncoder() {
        System.out.println(passwordEncoder.encode("test"));
    }
}
