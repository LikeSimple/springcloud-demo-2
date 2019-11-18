package com.newtouch.cloud.demo.service.userauthority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@MapperScan("com.newtouch.cloud.demo.service.userauthority.persistence.mapper")
public class UserAuthorityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthorityApplication.class, args);
	}

}
