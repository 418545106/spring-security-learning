package com.imooc.security.imoocsecuritycore.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 15:30
 * @Description:
 */
@Getter
@Setter
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();
}
