package com.newtouch.cloud.demo.service.auth.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemAuthority implements Serializable {
    private String id;

    private String name;

    private String description;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}