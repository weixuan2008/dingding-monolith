package com.hy.micro.service.dingding.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hy.micro.service.dingding.entity.UserInfo;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-03
 * @github: https://github.com/weixuan2008
 */
public interface UserInfoRepo extends JpaRepository<UserInfo, String> {
	
	/**
	 * TODO:
	 * @param userId
	 * @return
	 * @throws
	 */
	UserInfo findByUserId(String userId);
}
