package com.imooc.security.imoocsecuritycore.validate.controller;

import com.imooc.security.imoocsecuritycore.validate.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 13:20
 * @Description:
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 创建验证码
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessors.get(type+"CodeProcessor").create(new ServletWebRequest(request,response));
    }

}
