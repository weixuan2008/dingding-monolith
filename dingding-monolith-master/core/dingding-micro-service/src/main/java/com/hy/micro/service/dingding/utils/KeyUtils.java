package com.hy.micro.service.dingding.utils;

import java.util.Random;

/**
 * Generate the unique key
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class KeyUtils {
	public static synchronized String genKey() {
		Random random = new Random();
		Integer number = random.nextInt(900000) + 100000;

		return System.currentTimeMillis() + String.valueOf(number);
	}
}
