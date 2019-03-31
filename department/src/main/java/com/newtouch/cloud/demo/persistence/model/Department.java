package com.newtouch.cloud.demo.persistence.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Department implements Serializable {
    private Integer id;

    private String deptName;

    private Integer priDeptId;

    private static final long serialVersionUID = 1L;
}