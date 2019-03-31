package com.newtouch.cloud.demo.corporation.service;

import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceFallback implements DepartmentService {

    @Override
    public String getDepartment(int id) {
        return "Service Error!";
    }
}
