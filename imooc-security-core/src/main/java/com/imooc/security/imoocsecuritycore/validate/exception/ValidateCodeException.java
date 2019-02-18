package com.imooc.security.imoocsecuritycore.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 13:57
 * @Description:
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
