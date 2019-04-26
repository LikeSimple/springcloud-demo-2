package com.newtouch.cloud.demo.organization.service;

import com.newtouch.cloud.demo.organization.controller.vo.UserDetails;


public interface OrganizationService {

    UserDetails getUserDetails(String username);

}
