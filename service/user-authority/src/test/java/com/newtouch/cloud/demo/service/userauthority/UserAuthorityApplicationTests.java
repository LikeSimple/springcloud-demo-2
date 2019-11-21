package com.newtouch.cloud.demo.service.userauthority;

import com.newtouch.cloud.demo.service.userauthority.controller.criteria.UsernameCriteria;
import com.newtouch.cloud.demo.service.userauthority.persistence.mapper.LoginUserMapper;
import com.newtouch.cloud.demo.service.userauthority.persistence.model.LoginUser;
import com.newtouch.cloud.demo.service.userauthority.service.UserAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthorityApplication.class)
@AutoConfigureMockMvc
@Slf4j
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

	@Autowired
	private UserAuthorityService userAuthorityService;

	@Autowired
	private MockMvc mvc;

	private ObjectMapper objectMapper = new ObjectMapper();;

	public UserAuthorityApplicationTests() throws Exception {
	}

	@Test
	public void MappersTest() {
		LoginUser loginUser = loginUserMapper.getByUsername("test");
		Assert.notNull(loginUser, "没有找到登录用户，请检查初始化数据");
		System.out.println(loginUser.toString());
	}

	@Test
	public void serviceTest() {
		userAuthorityService.getByUsername("test");
	}

	@Test
	public void getUserById() throws Exception {
		System.out.println(
		mvc.perform(
				MockMvcRequestBuilders
						.post("/by-name")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept("*")
				.content(objectMapper.writeValueAsBytes(new UsernameCriteria("test"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString()
		);
	}

}
