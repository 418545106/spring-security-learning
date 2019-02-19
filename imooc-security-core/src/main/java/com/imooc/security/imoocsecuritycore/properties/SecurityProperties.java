package com.imooc.security.imoocsecuritycore.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 15:31
 * @Description:
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();

}
