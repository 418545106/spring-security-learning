package com.imooc.security.imoocsecuritycore.config;

/**
 * @auther: zpd
 * @Date: 2019/2/19 0019 09:21
 * @Description:
 */
public interface SecurityConstants {

    /**
     * 表单登陆提交路径
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 短信登陆提交路径
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATED_URL = "/authentication/require";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 默认登录页面
     */
    String DEFAULT_SIGN_IN_PAGE_URL = "/imooc-signIn.html";

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
}
