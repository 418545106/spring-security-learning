package com.imooc.security.imoocsecuritycore.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 15:25
 * @Description:
 */
@Getter
@Setter
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties(){
        super.setLength(4);
    }

    private Integer width = 85;
    private Integer height = 20;
}
