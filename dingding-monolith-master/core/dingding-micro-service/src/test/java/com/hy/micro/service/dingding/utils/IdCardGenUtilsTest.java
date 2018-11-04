package com.hy.micro.service.dingding.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public class IdCardGenUtilsTest {
	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			String card = IdCardGenUtils.generateID();
			Assert.assertNotEquals(null, card);
			System.out.println(card);
		}
	}

}
