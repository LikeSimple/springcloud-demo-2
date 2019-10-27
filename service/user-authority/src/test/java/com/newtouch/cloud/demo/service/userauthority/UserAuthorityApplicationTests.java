package com.newtouch.cloud.demo.service.userauthority;

import com.newtouch.cloud.demo.service.userauthority.persistence.mapper.LoginUserMapper;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthorityApplication.class)
//@ContextConfiguration(initializers = {UserAuthorityApplicationTests.Initializer.class})
public class UserAuthorityApplicationTests {

//	@ClassRule
//	private static MySQLContainer db = new MySQLContainer("oracle/database:11.2.0.2-xe");
//
//	@ClassRule
//	public static GenericContainer redis = new GenericContainer("redis:4.0.14-alpine")
//			.withExposedPorts(6379);
//
//	@ClassRule
//	public static GenericContainer rabbitMq = new GenericContainer("rabbitmq:3.7.19-management-alpine")
//			.withExposedPorts(5672)
//			.withEnv("RABBITMQ_DEFAULT_USER","rabbit")
//			.withEnv("RABBITMQ_DEFAULT_PASS", "123456");

//	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//		@Override
//		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//			TestPropertyValues.of(
//					"spring.redis.port=" + redis.getFirstMappedPort(),
//					"spring.rabbitmq.port=" + rabbitMq.getFirstMappedPort()
//			).applyTo(configurableApplicationContext);
//		}
//	}

	@Autowired
	private LoginUserMapper loginUserMapper;

	public UserAuthorityApplicationTests() {

	}

	@Test
	public void MappersTest() {
		loginUserMapper.getByUsername("");
	}

}
