package com.imooc.security.imoocsecuritydemo.config;

import com.imooc.security.imoocsecuritydemo.interceptor.customizeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther: zpd
 * @Date: 2019/1/18 0018 10:44
 * @Description:
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private customizeInterceptor customizeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customizeInterceptor);
    }
}
