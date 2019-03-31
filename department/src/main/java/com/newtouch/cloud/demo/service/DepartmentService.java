package com.newtouch.cloud.demo.service;

import com.netflix.discovery.converters.Auto;
import com.newtouch.cloud.demo.persistence.mapper.DepartmentMapper;
import com.newtouch.cloud.demo.persistence.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public Department getDepartment(int id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

}
