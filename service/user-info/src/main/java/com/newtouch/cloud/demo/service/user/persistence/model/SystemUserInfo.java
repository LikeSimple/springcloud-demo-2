package com.newtouch.cloud.demo.service.user.persistence.model;

import com.newtouch.cloud.demo.service.user.controller.criteral.UserCriteria;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SystemUserInfo implements Serializable {

    private String id;

    private String nickname;

    private String email;

    private String username;

    private static final long serialVersionUID = -3344899059480422103L;

    public SystemUserInfo(UserCriteria userCriteria) {
        this.id = userCriteria.getId();

        this.username = userCriteria.getUsername();

        this.email = userCriteria.getEmail();

        this.nickname = userCriteria.getNickName();
    }
}