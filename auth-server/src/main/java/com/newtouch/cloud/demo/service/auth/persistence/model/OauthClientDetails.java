package com.newtouch.cloud.demo.service.auth.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OauthClientDetails implements Serializable {
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

    private static final long serialVersionUID = 8703188792827098859L;
}