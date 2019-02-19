package com.imooc.security.imoocsecuritycore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @auther: zpd
 * @Date: 2019/2/19 0019 10:31
 * @Description: 表单登陆配置信息
 */
@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customizeAuthenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATED_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(customizeAuthenticationSuccessHandler)
            .failureHandler(customizeAuthenticationFailureHandler);
    }
}
