package com.imooc.security.imoocsecuritybrowser.config;

import com.imooc.security.imoocsecuritycore.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 14:02
 * @Description:
 */
@Component
@Order(value = Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {


    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.anyRequest().access("@rbacServiceImpl.hasPermission(request,authentication)");
    }
}
