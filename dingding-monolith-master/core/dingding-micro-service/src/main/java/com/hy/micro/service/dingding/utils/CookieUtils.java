package com.hy.micro.service.dingding.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The help class for cookie.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class CookieUtils {
	/**
	 * add cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void set(HttpServletResponse response, String name, String value, Integer maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * get cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie get(HttpServletRequest request, String name) {
		Cookie cookie = getCookieMap(request).get(name);
		if (null != cookie) {
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * convert cookie to map
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Map<String, Cookie> cookieMap = new HashMap<>();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
