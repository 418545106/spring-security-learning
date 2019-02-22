package com.imooc.security.imoocsecuritycore.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/2/22 0022 09:43
 * @Description:
 */
@Getter
@Setter
public class OAuth2Properties {

    private String jwtSigningKey = "yueqi";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
