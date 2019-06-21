package com.newtouch.cloud.demo.service.user;

import com.newtouch.cloud.demo.service.user.persistence.mapper.SystemUserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(systemUserMapper.selectByUsername("admin"));
    }

}
