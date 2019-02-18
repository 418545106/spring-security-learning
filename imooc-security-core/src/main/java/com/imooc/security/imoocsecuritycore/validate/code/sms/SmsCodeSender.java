package com.imooc.security.imoocsecuritycore.validate.code.sms;

/**
 * @auther: zpd
 * @Date: 2019/2/18 0018 10:00
 * @Description: 发送短信的接口
 */
public interface SmsCodeSender {

    void send(String mobile,String code);
}
