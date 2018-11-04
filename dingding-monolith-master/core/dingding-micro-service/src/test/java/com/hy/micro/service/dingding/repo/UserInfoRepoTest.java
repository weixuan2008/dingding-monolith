package com.hy.micro.service.dingding.repo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hy.micro.service.dingding.DingdingServiceTest;
import com.hy.micro.service.dingding.entity.UserInfo;
import com.hy.micro.service.dingding.enums.GenderType;
import com.hy.micro.service.dingding.enums.UserRole;
import com.hy.micro.service.dingding.repo.UserInfoRepo;
import com.hy.micro.service.dingding.utils.HashUtils;
import com.hy.micro.service.dingding.utils.IdCardGenUtils;
import com.hy.micro.service.dingding.utils.PhoneNumberGenUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-06
 * @github: https://github.com/weixuan2008
 */
@Slf4j
@Component
public class UserInfoRepoTest extends DingdingServiceTest{
	@Autowired
	private UserInfoRepo userInfoRepository;

	@Test
	public void saveTest() {
		UserInfo userInfo = new UserInfo();
		
		//userInfo.setUserId(IdCardGeneratorUtil.generateID());
		userInfo.setUserId("360428197006156666");
		userInfo.setUserRole(UserRole.ROLE_ADMIN);
		userInfo.setPhone(PhoneNumberGenUtils.getMobile());
		userInfo.setEmail("eddie.wei@hy.com");
		userInfo.setUsername("Eddie");
		userInfo.setPassword(HashUtils.hashWithMD5("123456"));
		userInfo.setOpenId("t549ba59abbe56");
		userInfo.setCountry("Chine");
		userInfo.setProvince("Beijing");
		userInfo.setCity("Beijing");
		userInfo.setAddress("HePing Street District No.12");
		userInfo.setGender(GenderType.GENDER_FEMALE);

		UserInfo result = userInfoRepository.save(userInfo);
		log.info(result.toString());
		
		Assert.assertNotEquals(null, result);
	}
	
	@Test
	public void saveMultiTest() {
		for(int i = 0; i < 3;i++){
			UserInfo userInfo = new UserInfo();
			
			userInfo.setUserId(IdCardGenUtils.generateID());
			userInfo.setUserRole(UserRole.ROLE_ADMIN);
			userInfo.setPhone(PhoneNumberGenUtils.getMobile());
			userInfo.setEmail("eddie.wei@hy.com");
			userInfo.setUsername("Eddie");
			userInfo.setPassword(HashUtils.hashWithMD5("123456"));
			userInfo.setOpenId("t549ba59abbe56");
			userInfo.setCountry("Chine");
			userInfo.setProvince("Beijing");
			userInfo.setCity("Beijing");
			userInfo.setAddress("HePing Street District No.12");
			userInfo.setGender(GenderType.GENDER_FEMALE);
	
			UserInfo result = userInfoRepository.save(userInfo);
			log.info(result.toString());
			
			Assert.assertNotEquals(null, result);
		}
	}
	
	@Test
	public void updateTest() {
		UserInfo userInfo = userInfoRepository.findByUserId("360428197006156666");
		
		userInfo.setCountry("China");
		
		UserInfo result = userInfoRepository.save(userInfo);
		log.info(result.toString());
		
		Assert.assertNotEquals(null, result);
	}

}
