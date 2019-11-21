package com.newtouch.cloud.demo.service.auth.api.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUser implements UserDetails,  Serializable {

    private SystemUser systemUser;

    private List<SystemAuthority> systemAuthorities;

    public LoginUser(SystemUser systemUser, List<SystemAuthority> systemAuthorities) {
        this.systemUser = systemUser;
        this.systemAuthorities = systemAuthorities;
    }
    public LoginUser(){};

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return systemAuthorities.stream().map(NewtouchAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return null == systemUser.getAccountExpire() || systemUser.getAccountExpire().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !systemUser.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return null == systemUser.getCredentialExpire() || systemUser.getCredentialExpire().after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return systemUser.getEnabled();
    }

    public static class NewtouchAuthority implements GrantedAuthority {

        private SystemAuthority systemAuthority;

        public NewtouchAuthority(SystemAuthority systemAuthority) {
            this.systemAuthority = systemAuthority;
        }

        @Override
        public String getAuthority() {
            return systemAuthority.getName();
        }
    }
}
