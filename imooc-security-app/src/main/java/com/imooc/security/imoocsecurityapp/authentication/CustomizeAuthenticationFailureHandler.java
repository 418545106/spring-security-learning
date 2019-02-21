package com.imooc.security.imoocsecurityapp.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.imoocsecuritycore.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.error("============== login failure !!");
        logger.error("exception : {}",exception);
        //json返回
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        //用ajax的方式返回
//        AjaxResultHandler resultHandler = new AjaxResultHandler(HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),"login failure");
//        response.getWriter().write(JSON.toJSONString(resultHandler));

        //调用父类的返回方式返回
        //super.onAuthenticationFailure(request,response,exception);
    }
}
