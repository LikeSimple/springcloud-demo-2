package com.newtouch.cloud.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.newtouch.cloud.demo.persistence.mapper")
@EnableEurekaClient
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}

}
