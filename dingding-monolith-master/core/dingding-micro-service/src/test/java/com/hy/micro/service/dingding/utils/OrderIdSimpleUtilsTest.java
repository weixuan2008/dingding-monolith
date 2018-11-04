package com.hy.micro.service.dingding.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIdSimpleUtilsTest {

	@Test
	public void test() {       
        String id = OrderIdSimpleUtils.generateOrderId();
        System.out.println(id);
        Assert.assertNotEquals("id cannot be null.", null, id);
	}

}
