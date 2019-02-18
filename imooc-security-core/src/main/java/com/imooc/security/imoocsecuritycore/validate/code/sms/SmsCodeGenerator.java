package com.imooc.security.imoocsecuritycore.validate.code.sms;

import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import com.imooc.security.imoocsecuritycore.validate.ValidateCodeGenerator;
import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 16:46
 * @Description:
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
