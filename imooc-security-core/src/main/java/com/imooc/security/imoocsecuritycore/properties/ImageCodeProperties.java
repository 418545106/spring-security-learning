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
public class ImageCodeProperties {

    private Integer width = 85;
    private Integer height = 20;
    private Integer length = 6;
    private Integer expireIn = 60;

    private String url;
}
