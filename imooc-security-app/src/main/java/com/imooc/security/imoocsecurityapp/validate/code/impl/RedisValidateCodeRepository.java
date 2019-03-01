/**
 * 
 */
package com.imooc.security.imoocsecurityapp.validate.code.impl;

import com.imooc.security.imoocsecuritycore.exception.ValidateCodeException;
import com.imooc.security.imoocsecuritycore.validate.ValidateCodeRepository;
import com.imooc.security.imoocsecuritycore.validate.code.ValidateCode;
import com.imooc.security.imoocsecuritycore.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * 
 * @author zhailiang
 *
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
	    redisTemplate.opsForValue().set("1","2");
//        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
		Object value = redisTemplate.opsForValue().get(buildKey(request, type));
		if (value == null) {
			return null;
		}
		return (ValidateCode) value;
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType type) {
		redisTemplate.delete(buildKey(request, type));
	}

	/**
	 * @param request
	 * @param type
	 * @return 在请求体中获取请求的手机号，把手机号作为主键
	 */
	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
//		String deviceId = request.getHeader("deviceId");
        String mobile = null;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        }catch (Exception e){
            e.printStackTrace();
        }
		if (StringUtils.isBlank(mobile)) {
			throw new ValidateCodeException("请在请求中携带mobile参数");
		}
		return "code:" + type.toString().toLowerCase() + ":" + mobile;
	}

}
