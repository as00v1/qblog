package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.api.common.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QblogWebApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    public void redisAdd() {
        redisService.set("test", "hello30", 30);
    }

    @Test
    public void redisGet() {
        String value = (String) redisService.get("test");
        System.out.println(value);
    }

}
