package com.imooc.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther: zpd
 * @Date: 2019/2/26 0026 15:41
 * @Description:
 */
@Component
public class RbacServiceImpl implements RbacService{

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;

        if(principal instanceof UserDetails){
            String username = ((UserDetails)principal).getUsername();
            //在获得用户的name之后就能去数据库查询用户的角色，已经用户对应的url
            //.....
            //cache
            Set<String> urls = new HashSet<>();
            urls.add("");
            for(String url: urls){
                if(antPathMatcher.match(url,request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
