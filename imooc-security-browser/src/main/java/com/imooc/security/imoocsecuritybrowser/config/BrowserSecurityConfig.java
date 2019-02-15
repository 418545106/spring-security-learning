package com.imooc.security.imoocsecuritybrowser.config;

import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationFailureHandler;
import com.imooc.security.imoocsecuritybrowser.config.authentication.CustomizeAuthenticationSuccessHandler;
import com.imooc.security.imoocsecuritybrowser.session.CustomizeExpiredSessionStrategy;
import com.imooc.security.imoocsecuritycore.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

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
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(customizeUserDetailService)
                .and()
            .authorizeRequests()
                .antMatchers("/authentication/require","/imooc-signIn.html","/code/image","/session/invalid")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .expiredSessionStrategy(new CustomizeExpiredSessionStrategy())
                .and()
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
