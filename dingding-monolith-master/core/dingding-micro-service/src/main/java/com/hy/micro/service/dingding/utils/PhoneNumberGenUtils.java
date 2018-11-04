package com.hy.micro.service.dingding.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-06 
 * @github: https://github.com/weixuan2008
 */
public class PhoneNumberGenUtils {
	// 判断是否电话格式
	public static boolean isMobileNO(String mobiles) {

		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher matcher = pattern.matcher(mobiles);

		return matcher.matches();
	}

	// 返回随机电话号码
	public static String getMobile() {

		while (true) {
			String randomPhone = randomPhone();
			if (PhoneNumberGenUtils.isMobileNO(randomPhone)) {
				return randomPhone;
			}

		}

	}

	// 产生随机电话号码格式数字
	private static String randomPhone() {
		String phone = "1";

		Random random = new Random();
		int nextInt = random.nextInt(3);

		if (nextInt == 0) {
			phone = phone + "3" + PhoneNumberGenUtils.randomNumber();
		} else if (nextInt == 1) {
			phone = phone + "5" + PhoneNumberGenUtils.randomNumber();
		} else {
			phone = phone + "8" + PhoneNumberGenUtils.randomNumber();
		}
		return phone;
	}

	// 生成长度为9的随机数
	private static String randomNumber() {

		Random random = new Random();
		int nextInt = random.nextInt(900000000) + 100000000;
		int abs = Math.abs(nextInt);
		String valueOf = String.valueOf(abs);
		return valueOf;
	}
}
