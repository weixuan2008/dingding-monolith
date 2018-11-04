package com.hy.micro.service.dingding.web.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.micro.service.dingding.config.ProjectUrlConfig;
import com.hy.micro.service.dingding.constant.CookieConst;
import com.hy.micro.service.dingding.constant.RedisConst;
import com.hy.micro.service.dingding.entity.UserInfo;
import com.hy.micro.service.dingding.enums.ResultCode;
import com.hy.micro.service.dingding.service.UserInfoService;
import com.hy.micro.service.dingding.utils.CookieUtils;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	/**
	 * TODO:
	 * @param userId
	 * @param response
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/login")
	public ModelAndView login(@RequestParam("userId") String userId, HttpServletResponse response,
			Map<String, Object> map) {

		// 1. check the userId from redis cache.
		UserInfo userInfo = userInfoService.findByUserId(userId);
		if (userInfo == null) {
			map.put("msg", ResultCode.LOGIN_FAILURE);
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error");
		}

		// 2. save the token to redis
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConst.REDIS_EXPIRE;

		redisTemplate.opsForValue().set(String.format(RedisConst.REDIS_PREFIX, token), userId, expire,
				TimeUnit.SECONDS);

		// 3. save the token to cookie
		CookieUtils.set(response, CookieConst.COOKIE_TOKEN, token, expire);

		return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/seller/order/list");

	}

	/**
	 * TODO:
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		// 1. 从cookie里查询
		Cookie cookie = CookieUtils.get(request, CookieConst.COOKIE_TOKEN);
		if (cookie != null) {
			// 2. clean redis
			redisTemplate.opsForValue().getOperations()
					.delete(String.format(RedisConst.REDIS_PREFIX, cookie.getValue()));

			// 3. clean cookie
			CookieUtils.set(response, CookieConst.COOKIE_TOKEN, null, 0);
		}

		map.put("msg", ResultCode.LOGOUT_SUCCESS);
		map.put("url", "/seller/order/list");
		return new ModelAndView("common/success", map);
	}
}
