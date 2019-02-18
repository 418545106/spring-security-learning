package com.imooc.security.imoocsecuritycore.validate.code.sms;

import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import com.imooc.security.imoocsecuritycore.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @auther: zpd
 * @Date: 2019/2/18 0018 11:40
 * @Description:
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
