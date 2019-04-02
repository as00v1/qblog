package com.qiaohx.qblog.qblogweb.controller.user;

import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.service.LoginService;
import com.qiaohx.qblog.api.user.service.RegisterService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

//    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CheckLoginCertService checkLoginCertService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/checkLoginCert", method = RequestMethod.POST)
    public BaseDataResponse checkLoginCert(@RequestBody LoginRequestVo loginRequestVo){
//        try {
            return checkLoginCertService.checkLoginCert(loginRequestVo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("校验登录名出错", e);
//            return ResponseUtil.error(ErrorCodeEnums.UNKNOW_ERROR);
//        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseDataResponse register(@RequestBody RegisterRequestVo registerInfoVo) throws Exception{
        return registerService.addRegisterUser(registerInfoVo);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseDataResponse login(@RequestBody LoginRequestVo loginRequestVo, HttpServletRequest request) throws Exception{
        String ip = request.getHeader("X-Real-IP");// nginx代理的真实IP
        if (null == ip || "".equals(ip))
            ip = request.getRemoteAddr();
        loginRequestVo.setIp(ip);
        return loginService.login(loginRequestVo);
    }

}
