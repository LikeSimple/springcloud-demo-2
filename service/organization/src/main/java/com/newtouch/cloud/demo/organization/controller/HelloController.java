package com.newtouch.cloud.demo.organization.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    @RequestMapping("/")
    public String hello() {
        return "Hello world! port:" + port;
    }
}
