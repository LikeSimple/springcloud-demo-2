package com.newtouch.cloud.demo.auth.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemRoleAuthority implements Serializable {
    private String roleId;

    private String authorityId;

    private Date createdTime;

    private static final long serialVersionUID = -6499116368105815448L;
}