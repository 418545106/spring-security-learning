package com.imooc.security.imoocsecuritydemo.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @auther: zpd
 * @Date: 2019/1/18 0018 11:18
 * @Description:
 */
@Aspect
@EnableAspectJAutoProxy
@Configuration
public class CustomizeAspect {

    private Logger logger = LoggerFactory.getLogger(CustomizeAspect.class);

    @Pointcut("execution(* com.imooc.security.imoocsecuritydemo.controller..*.*(..))")
    public void excudePoint(){}

    /*@Before("excudePoint()")
    public void befor(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
		logger.info("----->Befor Method");
		logger.info("----->Befor Method execution method name:{}",joinPoint.toString());
		logger.info("----->input parameter:{}",JSON.toJSONString(objects));
    }

    @After("excudePoint()")
	public void after(JoinPoint joinPoint) {
		logger.info("----->after Method Info");
	}*/

    @Around("excudePoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        logger.info("------------>Around Aspect");
        Object result = null;
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        try {
            logger.info("------------>Class Name: {}" , className);
            logger.info("------------>Method Name: {}" , methodName);
            logger.info("------------>input parameter: {}" , JSON.toJSONString(joinPoint.getArgs()));
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("------------>Have Exception , Class Name: {} ,Method Name: {}" ,className,methodName);
            logger.error("------------>Exception Info: {}",e);
        }
        logger.info("------------>Result:{}",JSON.toJSONString(result));
        return result;
    }
}
