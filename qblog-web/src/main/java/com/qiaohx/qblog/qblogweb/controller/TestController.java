package com.qiaohx.qblog.qblogweb.controller;

import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.util.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RedisService redisService;

    @RequestMapping("/qblog")
    public String qblog(){
        Test.out();
        return "hello qblog";
    }

    @RequestMapping("/redis-set")
    public String redisSet(HttpServletRequest request){
        logger.info("redis测试");
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        redisService.set(key, value);
        return "success";
    }

    @RequestMapping("/redis-get")
    public String redisGet(HttpServletRequest request){
        logger.info("redis测试");
        String key = request.getParameter("key");
        return (String) redisService.get(key);
    }
}
