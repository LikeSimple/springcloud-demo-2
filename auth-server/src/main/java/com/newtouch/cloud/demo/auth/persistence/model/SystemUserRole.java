package com.newtouch.cloud.demo.auth.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUserRole implements Serializable {
    private String systemUserId;

    private String roleId;

    private Date createdTime;

    private static final long serialVersionUID = -633034910401260887L;
}