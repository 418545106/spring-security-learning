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

    private ValidateCodeProperties code = new ValidateCodeProperties();

}