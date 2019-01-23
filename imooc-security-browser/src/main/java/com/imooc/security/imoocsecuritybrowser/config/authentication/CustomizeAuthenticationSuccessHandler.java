package com.imooc.security.imoocsecuritybrowser.config.authentication;

import com.alibaba.fastjson.JSON;
import com.imooc.security.imoocsecuritybrowser.config.AjaxResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: zpd
 * @Date: 2019/1/22 0022 13:10
 * @Description:
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("~~~~~~~~~ login success ! !");
        logger.info("authentication: {}", JSON.toJSON(authentication));

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        AjaxResultHandler resultHandler = new AjaxResultHandler(HttpStatus.OK,HttpStatus.OK.value(),"login success");
        response.getWriter().write(JSON.toJSONString(resultHandler));
    }
}
