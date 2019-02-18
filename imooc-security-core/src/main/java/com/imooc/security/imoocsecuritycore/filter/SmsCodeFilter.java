package com.imooc.security.imoocsecuritycore.filter;

import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import com.imooc.security.imoocsecuritycore.validate.ValidateCodeProcessor;
import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import com.imooc.security.imoocsecuritycore.exception.ValidateCodeException;
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
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

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
        String[] configUrls = StringUtils.split(securityProperties.getCode().getSms().getUrl(),",");
        for(String configUrl : configUrls){
            urls.add(configUrl);
        }
        urls.add("/authentication/mobile");
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

    /**
     * 验证图片校验码
     * @param servletWebRequest
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        /**
         * 根据url获取不同的session_key
         */
        String session_key = ValidateCodeProcessor.SESSION_KEY_PREFIX+"SMS";

        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, session_key);

        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"smsCode");

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }

        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,session_key);
            throw new ValidateCodeException("验证码过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest,session_key);
    }
}
