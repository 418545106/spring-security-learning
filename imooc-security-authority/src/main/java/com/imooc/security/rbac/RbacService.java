package com.imooc.security.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 15:38
 * @Description:
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
