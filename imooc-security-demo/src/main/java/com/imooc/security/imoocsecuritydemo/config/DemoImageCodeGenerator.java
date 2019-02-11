package com.imooc.security.imoocsecuritydemo.config;

import com.imooc.security.imoocsecuritycore.validate.ValidateCodeGenerator;
import com.imooc.security.imoocsecuritycore.validate.code.ImageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 17:05
 * @Description:
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ImageCode generate(ServletWebRequest request) {
        logger.info("自定义的图形验证码生成方案");
        return null;
    }
}
