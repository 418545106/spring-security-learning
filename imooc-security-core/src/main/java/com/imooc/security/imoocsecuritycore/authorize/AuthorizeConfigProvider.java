package com.imooc.security.imoocsecuritycore.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 13:27
 * @Description:
 */
public interface AuthorizeConfigProvider {
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
