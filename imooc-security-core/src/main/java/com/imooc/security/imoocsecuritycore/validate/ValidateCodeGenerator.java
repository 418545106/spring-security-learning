package com.imooc.security.imoocsecuritycore.validate;

import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 16:38
 * @Description:
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
