package com.imooc.security.imoocsecuritybrowser.config;

import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationFailureHandler;
import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationSuccessHandler;
import com.imooc.security.imoocsecuritycore.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 09:16
 * @Description:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomizeUserDetailService customizeUserDetailService;

    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .loginPage("/authentication/require")
            .loginProcessingUrl("/authentication/form")
            .successHandler(customizeAuthenticationSuccessHandler)
            .failureHandler(customizeAuthenticationFailureHandler)
            .and()
            .authorizeRequests()
            .antMatchers("/authentication/require","/imooc-signIn.html","/code/image")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable();
        /*http.formLogin()
                .loginProcessingUrl("/login")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customizeUserDetailService).passwordEncoder(passwordEncoder());
    }
}
