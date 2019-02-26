package com.imooc.security.imoocsecurityapp.config;

import com.imooc.security.imoocsecurityapp.jwt.CustomJwtTokenEnhancer;
import com.imooc.security.imoocsecuritycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @auther: zpd
 * @Date: 2019/2/22 0022 09:35
 * @Description:
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 当配置文件中imooc.security.oauth2.storeType = redis 时该内部类的所有配置都会生效
     */
    @Bean
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "storeType",havingValue = "redis")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 当配置文件中imooc.security.oauth2.storeType = jwt 时该内部类的所有配置都会生效，matchIfMissing = ture 时没有配置时也会生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "imooc.security.oauth2", name = "storeType",havingValue = "jwt",matchIfMissing = true)
    public static class JwtTokenConfig{

        @Autowired
        private SecurityProperties securityProperties;

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        /**
         * TokenStore 只关乎存取
         * @return
         */
//        @Bean
//        public TokenStore jwtTokenStore(){
//            return new JwtTokenStore(jwtAccessTokenConverter());
//        }

        @Bean
        public TokenStore jwtTokenStore(){
            return new RedisTokenStore(redisConnectionFactory);
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //可以实现密签
            accessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return accessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(name="jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new CustomJwtTokenEnhancer(securityProperties.getOauth2().getJwtSigningKey());
        }
    }

}
