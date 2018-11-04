package com.hy.micro.service.dingding.service;

import com.hy.micro.service.dingding.entity.UserInfo;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface UserInfoService {
	UserInfo findByUserId(String userId);

	UserInfo save(UserInfo userInfo);
}
