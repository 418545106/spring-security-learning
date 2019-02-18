package com.imooc.security.imoocsecuritycore.validate.code;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @auther: zpd
 * @Date: 2019/2/18 0018 09:46
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, Integer expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
