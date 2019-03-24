package com.qiaohx.qblogweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/qblog")
    public String qblog(){
        return "hello qblog";
    }

}
