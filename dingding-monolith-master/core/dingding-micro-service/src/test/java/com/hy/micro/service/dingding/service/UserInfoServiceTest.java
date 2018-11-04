package com.hy.micro.service.dingding.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hy.micro.service.dingding.entity.UserInfo;
import com.hy.micro.service.dingding.service.impl.UserInfoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoServiceTest {	
	@Autowired
	private UserInfoServiceImpl userInfoService;

	private static final String BUYER_ID = "360428197006156666";

	@Test
	public void findUserInfoById() {
		UserInfo result = userInfoService.findByUserId(BUYER_ID);

		Assert.assertEquals(BUYER_ID, result.getUserId());
	}

}
