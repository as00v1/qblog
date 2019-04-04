package com.qiaohx.qblog.qblogweb.controller.user;

import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.service.LoginService;
import com.qiaohx.qblog.api.user.service.RegisterService;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.LoginResponseVo;
import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description = "用户接口")
public class UserController {

//    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CheckLoginCertService checkLoginCertService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SelectUserInfoByCidService selectUserInfoByCidService;

    @ApiOperation(value = "检查用户名重复", notes = "用于注册账号时，检查用户名是否可用的接口")
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

    @ApiOperation(value = "用户注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseDataResponse register(@RequestBody RegisterRequestVo registerInfoVo) throws Exception{
        return registerService.addRegisterUser(registerInfoVo);
    }

    @ApiOperation(value = "用户登录接口", notes = "登陆后返回用户cid，后续用户相关操作以cid为准")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseVo login(@RequestBody LoginRequestVo loginRequestVo, HttpServletRequest request) throws Exception{
        String ip = request.getHeader("X-Real-IP");// nginx代理的真实IP
        if (null == ip || "".equals(ip))
            ip = request.getRemoteAddr();
        loginRequestVo.setIp(ip);
        return loginService.login(loginRequestVo);
    }

    /**
     * 根据cid定位用户信息
     * @param cid 用户标识
     * @return 用户信息
     * @throws Exception 异常
     */
    @ApiOperation(value = "用户信息查询接口")
    @RequestMapping(value = "/selectUserInfo", method = RequestMethod.GET)
    public UserInfoResponseVo selectUserInfo(@ApiParam(value = "用户唯一标识", example = "581d267befe0487ba55c1ecb1fda2491") String cid) throws Exception{
        return selectUserInfoByCidService.selectUserInfoByCid(cid);
    }
}
