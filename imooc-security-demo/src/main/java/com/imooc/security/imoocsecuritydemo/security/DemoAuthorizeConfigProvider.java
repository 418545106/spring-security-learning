package com.imooc.security.imoocsecuritydemo.security;

import com.imooc.security.imoocsecuritycore.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 14:02
 * @Description:
 */
//@Component
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {


    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().access("@rbacServiceImpl.hasPermission(request,authentication)");
    }
}
