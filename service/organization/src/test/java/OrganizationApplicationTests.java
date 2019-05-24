import com.newtouch.cloud.demo.organization.OrganizationApplication;
import com.newtouch.cloud.demo.organization.persistence.mapper.CorporationMapper;
import org.jetbrains.annotations.NotNull;
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

@ActiveProfiles("test")
@SpringBootTest(classes = OrganizationApplication.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {OrganizationApplicationTests.Initializer.class})
public class OrganizationApplicationTests {

    @Autowired
    private CorporationMapper corporationMapper;

    @ClassRule
    private static MySQLContainer db = new MySQLContainer("mysql:5.7.22");


    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:4.0.12-alpine")
            .withExposedPorts(6379);

    @ClassRule
    public static GenericContainer rabbitMq = new GenericContainer("rabbitmq:3.7.7-management-alpine")
            .withExposedPorts(5672)
            .withEnv("RABBITMQ_DEFAULT_USER","rabbit")
            .withEnv("RABBITMQ_DEFAULT_PASS", "123456");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.redis.port=" + redis.getFirstMappedPort(),
                    "spring.rabbitmq.port=" + rabbitMq.getFirstMappedPort()
            ).applyTo(configurableApplicationContext);
        }
    }

    @Test
    public void dbTest() {
        corporationMapper.selectAll();
    }
}
