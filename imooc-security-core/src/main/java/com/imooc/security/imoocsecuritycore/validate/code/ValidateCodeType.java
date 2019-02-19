package com.imooc.security.imoocsecuritycore.validate.code;

import com.imooc.security.imoocsecuritycore.config.SecurityConstants;

/**
 * @auther: zpd
 * @Date: 2019/2/19 0019 09:17
 * @Description:
 */
public enum ValidateCodeType {
    /**
     * 图形验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },
    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    public abstract String getParamNameOnValidate();
}
