package com.newtouch.cloud.demo.auth.service.userauthority;

import com.newtouch.cloud.demo.service.user.UserInfoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserInfoApplication.class)
@AutoConfigureMockMvc
@Slf4j
//@ContextConfiguration(initializers = {UserAuthorityApplicationTests.Initializer.class})
public class UserInfoApplicationTests {

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
	private MockMvc mvc;

//	@Test
//	public void getUserById() throws Exception {
//		System.out.println(
//				mvc.perform(
//						MockMvcRequestBuilders
//								.post("/by-name")
//								.contentType(MediaType.APPLICATION_JSON_UTF8)
//								.accept("*")
//								.content(objectMapper.writeValueAsBytes(new UsernameCriteria("test"))))
//						.andExpect(MockMvcResultMatchers.status().isOk())
//						.andReturn().getResponse().getContentAsString()
//		);
//	}

}
