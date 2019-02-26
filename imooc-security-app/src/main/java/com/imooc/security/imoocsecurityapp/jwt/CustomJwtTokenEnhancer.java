package com.imooc.security.imoocsecurityapp.jwt;

import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: zpd
 * @Date: 2019/2/22 0022 10:34
 * @Description:
 */
public class CustomJwtTokenEnhancer implements TokenEnhancer{

    public CustomJwtTokenEnhancer(String key){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //可以实现密签
        accessTokenConverter.setSigningKey(key);
//        return accessTokenConverter;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String,Object> info = new HashMap<>();
        info.put("company","uuz");

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
