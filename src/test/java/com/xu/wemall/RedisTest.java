package com.xu.wemall;

import com.xu.wemall.components.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WeMallApplication.class)
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    private final String TOKEN_KEY = "access_token";

    @Test
    public void restTemplateTest() {

        long time = redisUtil.getExpire(TOKEN_KEY);
        log.info("time:{}",time);
    }
}
