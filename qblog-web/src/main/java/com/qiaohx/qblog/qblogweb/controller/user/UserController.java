package com.qiaohx.qblog.qblogweb.controller.user;

import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CheckLoginCertService checkLoginCertService;

    @RequestMapping("/checkLoginCert")
    public String checkLoginCert(@RequestBody LoginRequestVo loginRequestVo){
        try {
            checkLoginCertService.checkLoginCert(loginRequestVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello qblog";
    }

}
