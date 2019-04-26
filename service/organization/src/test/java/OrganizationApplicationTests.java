import com.newtouch.cloud.demo.organization.OrganizationApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@ActiveProfiles("test")
@SpringBootTest(classes = OrganizationApplication.class)
@RunWith(SpringRunner.class)
public class OrganizationApplicationTests {

    @Rule
    public GenericContainer redis = new GenericContainer<>("redis:4.0.11-alpine")
            .withExposedPorts(6379).withEnv("TZ", "Asia/Shanghai");

    @Rule
    public GenericContainer mq = new GenericContainer<>("rabbitmq:3.7.7-alpine")
            .withExposedPorts(5672).withEnv("TZ", "Asia/Shanghai");

    @Rule
    public GenericContainer db = new GenericContainer<>("percona:5.7")
            .withExposedPorts(3306).withEnv("TZ", "Asia/Shanghai")
            .withEnv("MYSQL_DATABASE","spring_cloud_demo_organization")
            .withEnv("MYSQL_ROOT_PASSWORD", "1234")
            .withCommand("--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --ignore-db-dir=lost+found");

    @Before
    public void initEnvironment() {
        redis.start();
        mq.start();
        db.start();
    }

    @Test
    public void demoTest() throws InterruptedException {
        Thread.sleep(6000);
    }

    @After
    public void destroyEnvironment() {
        redis.close();
        mq.close();
        db.close();
    }
}
