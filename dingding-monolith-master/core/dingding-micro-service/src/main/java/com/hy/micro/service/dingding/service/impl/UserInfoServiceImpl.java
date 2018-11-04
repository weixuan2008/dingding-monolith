package com.hy.micro.service.dingding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.entity.UserInfo;
import com.hy.micro.service.dingding.repo.UserInfoRepo;
import com.hy.micro.service.dingding.service.UserInfoService;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoRepo userInfoRepository;

	@Override
	public UserInfo findByUserId(String userId) {
		return userInfoRepository.findByUserId(userId);
	}

	@Override
	public UserInfo save(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}
}
