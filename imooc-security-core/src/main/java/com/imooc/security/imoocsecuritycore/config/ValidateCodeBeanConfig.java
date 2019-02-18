package com.imooc.security.imoocsecuritycore.config;

import com.imooc.security.imoocsecuritycore.validate.ValidateCodeGenerator;
import com.imooc.security.imoocsecuritycore.validate.code.image.ImageCodeGenerator;
import com.imooc.security.imoocsecuritycore.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.imoocsecuritycore.validate.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 16:52
 * @Description: 配置验证码的bean
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 装配验证码的bean，如果在spring IOC中已经存在名为<i>imageCodeGenerator</i>的bean,则该bean不会被装配
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
