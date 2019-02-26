package com.imooc.security.imoocsecuritycore.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 13:47
 * @Description:
 */
public interface AuthorizeConfigManager {

    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
