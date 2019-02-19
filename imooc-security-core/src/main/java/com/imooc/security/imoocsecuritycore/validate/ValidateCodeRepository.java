package com.imooc.security.imoocsecuritycore.validate;

import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import com.imooc.security.imoocsecuritycore.validate.code.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther: zpd
 * @Date: 2019/2/19 0019 10:53
 * @Description: 对session中的验证码操作
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);
}
