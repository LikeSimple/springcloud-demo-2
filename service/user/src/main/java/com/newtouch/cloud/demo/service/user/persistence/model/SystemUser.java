package com.newtouch.cloud.demo.service.user.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUser implements Serializable {
    private String id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private Date accountExpire;

    private Date credentialExpire;

    private static final long serialVersionUID = 1L;
}