package com.newtouch.cloud.demo.organization.persistence.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Department implements Serializable {
    private Integer deptId;

    private String deptName;

    private Integer prevDeptId;

    private Integer corpId;

    private static final long serialVersionUID = -1880096496311138312L;
}