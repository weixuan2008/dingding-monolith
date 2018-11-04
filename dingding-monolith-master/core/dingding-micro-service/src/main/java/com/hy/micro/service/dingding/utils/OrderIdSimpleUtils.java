package com.hy.micro.service.dingding.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Generate the unique id with simple random algorithm.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-03
 * @github: https://github.com/weixuan2008
 */
public class OrderIdSimpleUtils {
	public static synchronized String generateOrderId() {
		//SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");  
		SimpleDateFormat sdt=new SimpleDateFormat("YYYYMMddHHmmssSSS");
		
		String now = sdt.format(new Date());
			
		Random random = new Random();
		Integer number = random.nextInt(900000) + 100000;

		return now + String.valueOf(number);
	}
}
