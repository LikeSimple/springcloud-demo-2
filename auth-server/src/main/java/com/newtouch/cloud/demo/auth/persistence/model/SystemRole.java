package com.newtouch.cloud.demo.auth.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemRole implements Serializable {
    private String id;

    private String name;

    private String description;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = -7700162355677147043L;
}