package com.imooc.security.imoocsecuritycore.validate.controller;

import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import com.imooc.security.imoocsecuritycore.validate.ValidateCodeGenerator;
import com.imooc.security.imoocsecuritycore.validate.code.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 13:20
 * @Description:
 */
@RestController
public class ValidateCodeController {

    /**
     * session中存放的验证码key
     */
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    /**
     * 利用spring提供的SessionStrategy对session进行操作
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    /**
     * 为前段提供验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }


}
