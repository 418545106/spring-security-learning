package com.imooc.security.imoocsecuritycore.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: zpd
 * @Date: 2019/2/18 0018 10:01
 * @Description:
 */
public class DefaultSmsCodeSender implements  SmsCodeSender{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        logger.info("向手机{}发送短信验证码{}", mobile,code);
    }
}
