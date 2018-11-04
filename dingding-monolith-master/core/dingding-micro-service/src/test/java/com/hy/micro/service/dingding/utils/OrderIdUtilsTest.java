package com.hy.micro.service.dingding.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIdUtilsTest {
	@Autowired
	private OrderIdUtils OrderIdUtils;

	@Test
	public void test() {
		long id = OrderIdUtils.nextId();
		System.out.println(Long.toBinaryString(id));
		System.out.println(id);
		Assert.assertNotEquals("id must be greate than 0", 0, id);
	}

}
