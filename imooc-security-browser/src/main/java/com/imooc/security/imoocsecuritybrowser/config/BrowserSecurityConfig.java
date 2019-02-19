package com.imooc.security.imoocsecuritybrowser.config;

import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationFailureHandler;
import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationSuccessHandler;
import com.imooc.security.imoocsecuritybrowser.session.CustomizeExpiredSessionStrategy;
import com.imooc.security.imoocsecuritycore.config.FormAuthenticationConfig;
import com.imooc.security.imoocsecuritycore.config.SecurityConstants;
import com.imooc.security.imoocsecuritycore.config.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.imoocsecuritycore.config.ValidateCodeSecurityConfig;
import com.imooc.security.imoocsecuritycore.filter.ValidateCodeFilter;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 09:16
 * @Description:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomizeUserDetailService customizeUserDetailService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 配置关于验证码验证过滤器
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 表单登陆配置信息
     */
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        //用来记录用户token
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //在初次运行时创建关于用户session Token的数据表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(customizeUserDetailService)
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    SecurityConstants.DEFAULT_UNAUTHENTICATED_URL,
                    securityProperties.getBrowser().getSignInPage(),
                    SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*","/session/invalid")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            /*.sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .expiredSessionStrategy(new CustomizeExpiredSessionStrategy())
                .and()
                .and()*/
            .csrf()
            .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customizeUserDetailService).passwordEncoder(passwordEncoder());
    }
}
