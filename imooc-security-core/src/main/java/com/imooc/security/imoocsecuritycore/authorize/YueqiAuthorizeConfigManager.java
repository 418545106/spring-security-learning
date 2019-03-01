package com.imooc.security.imoocsecuritycore.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 13:50
 * @Description:
 */
@Component
public class YueqiAuthorizeConfigManager implements AuthorizeConfigManager{

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for(AuthorizeConfigProvider provider : authorizeConfigProviders){
            provider.config(config);
        }
        config.anyRequest().authenticated();
    }

}
