package com.newtouch.cloud.demo.corporation.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-department", fallback = DepartmentServiceFallback.class)
public interface DepartmentService {

    @GetMapping("/department/{id}")
    public String getDepartment(@PathVariable("id") int id);

}
