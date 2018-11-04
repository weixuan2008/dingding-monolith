package com.hy.micro.service.dingding.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hy.micro.service.dingding.constant.CookieConst;
import com.hy.micro.service.dingding.constant.RedisConst;
import com.hy.micro.service.dingding.exception.UserAuthorizeException;
import com.hy.micro.service.dingding.utils.CookieUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * User authorization checking with aop.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Aspect
@Slf4j
@Component
public class UserAuthorizeAspect {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// @Pointcut("execution(public * com.hy.micro.service.dingding.web.controller.UserController.*(..))"+
	@Pointcut("execution(public * com.hy.micro.service.dingding.web.controller.User*.*(..))" +
	" && !execution(public * com.hy.micro.service.dingding.web.controller.UserController.*(..))")
	public void verify() {

	}
	
	@Before("verify()")
	public void doVerify() {
		// 1. get request
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		
		// 2. query from cookies
		Cookie cookie = CookieUtils.get(request, CookieConst.COOKIE_TOKEN);
		if(null == cookie) {
			log.warn("Failed to login because cannot find token in cookie.");
			throw new UserAuthorizeException();
		}
		
		// 3. query from redis
		String openid = stringRedisTemplate.opsForValue().get(String.format(RedisConst.REDIS_PREFIX, cookie.getValue()));
		if(StringUtils.isEmpty(openid)) {
			log.warn("Failed to login because cannot find token in redis.");
            throw new UserAuthorizeException();
		}
		
	}
}
