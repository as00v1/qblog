package com.qiaohx.qblog.qblogweb.controller;

import com.qiaohx.util.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/qblog")
    public String qblog(){
        Test.out();
        return "hello qblog";
    }

}
