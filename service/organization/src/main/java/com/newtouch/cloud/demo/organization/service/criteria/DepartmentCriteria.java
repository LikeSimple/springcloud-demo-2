package com.newtouch.cloud.demo.organization.service.criteria;

import lombok.Data;

@Data
public class DepartmentCriteria {

    private String id;

    private String name;

    private String parentDepartmentId;

    private String corporationId;

}
