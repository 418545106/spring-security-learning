package com.imooc.security.imoocsecurityapp;

import com.imooc.security.imoocsecuritycore.authorize.AuthorizeConfigManager;
import com.imooc.security.imoocsecuritycore.config.SecurityConstants;
import com.imooc.security.imoocsecuritycore.config.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.imoocsecuritycore.config.ValidateCodeSecurityConfig;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @auther: zpd
 * @Date: 2019/2/21 0021 11:19
 * @Description:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    /**
     * 配置关于验证码验证过滤器
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATED_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
            .successHandler(customizeAuthenticationSuccessHandler)
            .failureHandler(customizeAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)
            .and()
            .apply(smsCodeAuthenticationSecurityConfig)
            .and()
            .csrf()
            .disable();

        authorizeConfigManager.configure(http.authorizeRequests());
    }

}
