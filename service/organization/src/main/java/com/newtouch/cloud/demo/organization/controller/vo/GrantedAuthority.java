package com.newtouch.cloud.demo.organization.controller.vo;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {
    String getAuthority();
}