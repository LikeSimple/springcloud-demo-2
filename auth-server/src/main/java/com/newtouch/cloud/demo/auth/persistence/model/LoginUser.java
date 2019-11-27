package com.newtouch.cloud.demo.auth.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginUser implements  Serializable {

    private SystemUser systemUser;

    private List<SystemAuthority> systemAuthorities;

}
