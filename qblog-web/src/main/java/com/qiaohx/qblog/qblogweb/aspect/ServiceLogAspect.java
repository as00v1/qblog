package com.qiaohx.qblog.qblogweb.aspect;

import com.qiaohx.util.date.DateFormatRules;
import com.qiaohx.util.date.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * 服务日志切面
 */
@Aspect
@Component
public class ServiceLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * 切点
     */
    @Pointcut("execution(public * com.qiaohx.qblog.service.*.service.*.*(..) )")
    public void serviceLog(){}

    /**
     * 方法执行之前切入
     * @param joinPoint
     */
    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 记录下请求内容
        logger.info(String.format("======= SERVICE %s START =======", method));
        logger.info("调用接口 : " + method);
        logger.info("接口入参 : " + Arrays.toString(joinPoint.getArgs()));
        ;
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        logger.info("接口返回 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("serviceLog()")
    public void afterThrowing(JoinPoint joinPoint){
        logger.error(DateUtil.dateToStr(DateFormatRules.YYYY_MM_DD_HH_MM_SS) + " ERROR");
        logger.error("接口抛出异常");
        logger.error("调用接口 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.error("接口入参 : " + Arrays.toString(joinPoint.getArgs()));
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("serviceLog()")
    public void after(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 记录下请求内容
        logger.info(String.format("======= SERVICE %s END =======", method));
    }
}
