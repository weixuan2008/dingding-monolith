package com.hy.micro.service.dingding.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-13
 * @github: https://github.com/weixuan2008
 */
public class JsonUtil {

	public static String toJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
