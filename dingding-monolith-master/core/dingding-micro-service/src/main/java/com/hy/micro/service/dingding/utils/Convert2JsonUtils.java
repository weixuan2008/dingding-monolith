package com.hy.micro.service.dingding.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Convert to json format
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class Convert2JsonUtils {
	public static String toJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
