package com.newtouch.cloud.demo.auth.persistence.model;

import com.newtouch.cloud.demo.auth.message.UserRegistrationMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class SystemUser implements Serializable {
    private String id;

    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private Date accountExpire;

    private Date credentialExpire;

    private Date createdTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1136209829987095175L;

    public SystemUser(UserRegistrationMessage userRegistrationMessage, PasswordEncoder passwordEncoder) {

        this.id = userRegistrationMessage.getId();

        this.username = userRegistrationMessage.getUsername();

        this.password = passwordEncoder.encode(userRegistrationMessage.getPassword().trim());

        this.enabled = Boolean.TRUE;

        this.locked = Boolean.FALSE;

        createdTime = new Date();

    }
}