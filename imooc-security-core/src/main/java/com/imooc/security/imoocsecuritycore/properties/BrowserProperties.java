package com.imooc.security.imoocsecuritycore.properties;

import com.imooc.security.imoocsecuritycore.config.SecurityConstants;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zpd
 * @Date: 2019/2/15 0015 10:19
 * @Description:
 */
@Getter
@Setter
public class BrowserProperties {

    /**
     * '记住我'功能的有效时间，默认1小时
     */
    private Integer rememberMeSeconds = 3600;

    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
}
