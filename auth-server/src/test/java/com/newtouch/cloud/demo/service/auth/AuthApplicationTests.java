package com.newtouch.cloud.demo.service.auth;

import com.newtouch.cloud.demo.service.auth.persistence.mapper.OauthClientDetailsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

    @Autowired
    private OauthClientDetailsMapper mapper;

    @Test
    public void contextLoad() {

        Assert.notNull(mapper.selectByPrimaryKey("web_app"), "没有找到对应的应用系统账户");
    }
}
