package com.hy.micro.service.dingding.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class PhoneNumberGenUtilsTest {

	@Test
	public void test() {
		int test = 200;
		while (test > 0) {
			String phoneNumber = PhoneNumberGenUtils.getMobile();
			Assert.assertNotEquals(null, phoneNumber);
			System.out.println(phoneNumber);
			test--;
		}
	}

}
