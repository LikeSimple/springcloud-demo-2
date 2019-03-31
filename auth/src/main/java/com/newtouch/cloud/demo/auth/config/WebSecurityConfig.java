package com.newtouch.cloud.demo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder(4))
                .withUser("reader")
                .password("$2a$04$OP7AtqdxOIuRRKPq7rWTyekwMGQ4eN0yMQSYLjvu/va.GgECuYnO.")
                .authorities("FOO_READ")
                .and()
                .withUser("writer")
                .password("$2a$04$341k53UOmKZ5Yy8D6ZJn5eVOPAJtCct.rZJ/YASbesoYi70I325NG")
                .authorities("FOO_READ", "FOO_WRITE");
    }

}
