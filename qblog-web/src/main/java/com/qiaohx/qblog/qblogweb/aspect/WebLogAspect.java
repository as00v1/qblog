package com.qiaohx.qblog.qblogweb.aspect;

import com.qiaohx.util.date.DateFormatRules;
import com.qiaohx.util.date.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 请求日志切面
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    /**
     * 切点
     */
    @Pointcut("execution(public * com.qiaohx.qblog.qblogweb.controller.*.*.*(..) )")
    public void webLog(){}

    /**
     * 方法执行之前切入
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 记录下请求内容
        logger.info(String.format("======= API %s START =======", method));
        logger.info("SESSION_ID : " + request.getSession().getId());
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("REAL IP : " + request.getHeader("X-Real-IP"));// nginx放的真实IP
        logger.info("CLASS_METHOD : " + method);
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        ;
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        logger.info("API返回 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void afterThrowing(JoinPoint joinPoint){
        logger.error(DateUtil.dateToStr(DateFormatRules.YYYY_MM_DD_HH_MM_SS) + " ERROR");
        logger.error("API抛出异常");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.error("SESSION_ID : " + request.getSession().getId());
        logger.error("URL : " + request.getRequestURL().toString());
        logger.error("HTTP_METHOD : " + request.getMethod());
        logger.error("IP : " + request.getRemoteAddr());
        logger.error("REAL IP : " + request.getHeader("X-Real-IP"));
        logger.error("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.error("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    // 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 打出结束参数
        logger.info("SESSION_ID : " + request.getSession().getId());
        logger.info(String.format("======= API %s END =======", method));
    }
}
