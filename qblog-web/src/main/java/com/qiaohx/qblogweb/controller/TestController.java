package com.qiaohx.qblogweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/qblog")
    public String qblog(){
        return "hello qblog";
    }

}
