package com.imooc.security.imoocsecuritybrowser.config;

import com.imooc.security.imoocsecuritybrowser.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @auther: zpd
 * @Date: 2019/1/21 0021 10:34
 * @Description:
 */
@Component
public class CustomizeUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomizeUserDetailService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("---------- login user name is : {}",username);
        String password = userDao.getUserByName(username);
        String res = new BCryptPasswordEncoder().encode(password);
        logger.info("---------- encoding password : {}",res);
        return new User(username, res , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return new User(username, password , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
