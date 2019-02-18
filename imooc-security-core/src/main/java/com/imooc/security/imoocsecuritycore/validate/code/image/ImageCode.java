package com.imooc.security.imoocsecuritycore.validate.code.image;

import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 13:14
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
public class ImageCode extends ValidateCode implements Serializable {

    private BufferedImage image;

    public ImageCode(BufferedImage image,String code,Integer expireIn){
        super(code,expireIn);
        this.image = image;
    }
}
