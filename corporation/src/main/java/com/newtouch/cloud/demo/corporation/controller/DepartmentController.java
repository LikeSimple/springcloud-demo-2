package com.newtouch.cloud.demo.corporation.controller;

import com.newtouch.cloud.demo.corporation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public String getDept(@PathVariable("id") int id) {
        return departmentService.getDepartment(id);
    }
}
