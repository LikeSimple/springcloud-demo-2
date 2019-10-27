package com.newtouch.cloud.demo.service.userauthority.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemRole implements Serializable {
    private String id;

    private String name;

    private String description;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}