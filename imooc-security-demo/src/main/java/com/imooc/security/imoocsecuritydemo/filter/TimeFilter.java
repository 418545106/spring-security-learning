package com.imooc.security.imoocsecuritydemo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @auther: zpd
 * @Date: 2019/1/18 0018 10:03
 * @Description: 用时javax自带的过滤器对servlet进行过滤处理
 */
//@Component
public class TimeFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("-------------------> Init Customize TimeFilter");

    }

    @Override
    public void destroy() {
        logger.info("-------------------> Destroy Customize TimeFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("-------------------> Do Customize TimeFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
