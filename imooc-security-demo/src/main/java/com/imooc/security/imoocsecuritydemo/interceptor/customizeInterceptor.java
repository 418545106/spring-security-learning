package com.imooc.security.imoocsecuritydemo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther: zpd
 * @Date: 2019/1/18 0018 10:24
 * @Description: spring自带的拦截器
 */
@Component
public class customizeInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(customizeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("--customizeInterceptor preHandle");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("--customizeInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("--customizeInterceptor afterCompletion");

        logger.info("--exception:{}",ex);

    }
}
