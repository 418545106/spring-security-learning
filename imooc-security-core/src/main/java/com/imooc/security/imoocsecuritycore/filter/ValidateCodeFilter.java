package com.imooc.security.imoocsecuritycore.filter;

import com.imooc.security.imoocsecuritycore.exception.ValidateCodeException;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import com.imooc.security.imoocsecuritycore.validate.code.ImageCode;
import com.imooc.security.imoocsecuritycore.validate.controller.ValidateCodeController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther: zpd
 * @Date: 2019/2/11 0011 13:43
 * @Description:
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler customizeAuthenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化bean时需要把配置中的urls装配到Set集合中
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.split(securityProperties.getCode().getImage().getUrl(),",");
        for(String configUrl : configUrls){
            urls.add(configUrl);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        boolean action = false;

        //when request is in security properties
        for(String url: urls){
            if(pathMatcher.match(url,request.getRequestURI())){
                action = true;
            }
        }

        if(action){
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                customizeAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return ;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
    }
}
