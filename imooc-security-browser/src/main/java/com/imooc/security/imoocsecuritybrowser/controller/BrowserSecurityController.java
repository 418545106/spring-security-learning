package com.imooc.security.imoocsecuritybrowser.controller;

import com.imooc.security.imoocsecuritybrowser.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: zpd
 * @Date: 2019/1/22 0022 10:54
 * @Description:
 */
@RestController
public class BrowserSecurityController {

    /**
     * 请求缓存，内含包括请求API还是HTML请求
     * 获取session中的请求缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);

    /**
     * 重定向的类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private static final String URL_END_WITH_HTML = ".html";

    /**
     * when need role authentication
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获得之前保存的session请求缓存
        SavedRequest savedRequest = requestCache.getRequest(request,response);

        if(savedRequest != null){
            //获取请求的URL
            String target = savedRequest.getRedirectUrl();
            logger.info("------------- the target url is : {}" ,target);
            if(StringUtils.endsWithIgnoreCase(target,URL_END_WITH_HTML)){
                redirectStrategy.sendRedirect(request,response,"/imooc-signIn.html");
            }
        }
        
        return new SimpleResponse("no role authentication , please login first !");
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid(HttpServletRequest request){
        return new SimpleResponse("长时间未活动，已自动下线,请重新登录");
    }
}
