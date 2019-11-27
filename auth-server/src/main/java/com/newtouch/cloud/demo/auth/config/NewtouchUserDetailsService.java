package com.newtouch.cloud.demo.auth.config;


import com.newtouch.cloud.demo.auth.persistence.model.LoginUser;
import com.newtouch.cloud.demo.auth.persistence.model.SystemAuthority;
import com.newtouch.cloud.demo.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class NewtouchUserDetailsService implements UserDetailsService {

    private UserService userService;

    public NewtouchUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = userService.searchLoginUserByName(username);
        log.info("获得登录用户信息" + loginUser.toString());
        return new NewtouchUserDetails(loginUser);
    }

    @Bean
    public NewtouchUserDetailsService getUserDetailsService(UserService userService) {
        return new NewtouchUserDetailsService(userService);
    }

    private static class NewtouchUserDetails implements UserDetails {

        private LoginUser loginUser;

        public NewtouchUserDetails(LoginUser loginUser) {
            this.loginUser = loginUser;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return loginUser.getSystemAuthorities().stream().map(NewtouchUserDetailsService.NewtouchUserDetails.NewtouchAuthority::new).collect(Collectors.toList());
        }

        @Override
        public String getPassword() {
            return loginUser.getSystemUser().getPassword();
        }

        @Override
        public String getUsername() {
            return loginUser.getSystemUser().getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return null == loginUser.getSystemUser().getAccountExpire() || loginUser.getSystemUser().getAccountExpire().after(new Date());
        }

        @Override
        public boolean isAccountNonLocked() {
            return !loginUser.getSystemUser().getLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return null == loginUser.getSystemUser().getCredentialExpire() || loginUser.getSystemUser().getCredentialExpire().after(new Date());
        }

        @Override
        public boolean isEnabled() {
            return loginUser.getSystemUser().getEnabled();
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
}
