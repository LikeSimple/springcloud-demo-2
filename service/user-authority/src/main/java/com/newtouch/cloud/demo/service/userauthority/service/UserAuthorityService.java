package com.newtouch.cloud.demo.service.userauthority.service;

import com.newtouch.cloud.demo.service.userauthority.controller.criteria.SystemUserCriteria;
import com.newtouch.cloud.demo.service.userauthority.persistence.model.SystemUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserAuthorityService {

    UserDetails getByUsername(String username);

    List<SystemUser> getSystemUsers(SystemUserCriteria systemUserCriteria, int pageNum, int pageSize);

    SystemUser getSystemUser(String id);

    void resetPassword(String id, String oldPassword, String newPassword);

    void newSystemUser(SystemUser systemUser);

}
