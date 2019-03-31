package com.newtouch.cloud.demo.controller;

import com.newtouch.cloud.demo.persistence.model.Department;
import com.newtouch.cloud.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public String getDepartment(@PathVariable("id") int id) {
        return departmentService.getDepartment(id).toString() + " port:" + port;
    }

}
