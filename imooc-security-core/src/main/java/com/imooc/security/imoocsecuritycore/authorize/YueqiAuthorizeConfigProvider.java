package com.imooc.security.imoocsecuritycore.authorize;

import com.imooc.security.imoocsecuritycore.config.SecurityConstants;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 13:35
 * @Description:
 */
@Component
@Order(Integer.MIN_VALUE)
public class YueqiAuthorizeConfigProvider implements AuthorizeConfigProvider{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
            SecurityConstants.DEFAULT_UNAUTHENTICATED_URL,
            securityProperties.getBrowser().getSignInPage(),
            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*","/session/invalid","/oauth/**"
        ).permitAll();
    }
}
