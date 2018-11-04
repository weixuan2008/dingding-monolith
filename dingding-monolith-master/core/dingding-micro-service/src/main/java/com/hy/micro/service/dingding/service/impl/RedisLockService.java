package com.hy.micro.service.dingding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */

@Service
@Slf4j
public class RedisLockService {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 
	 * lock function
	 * @param key
	 * @param value
	 * @return
	 * @throws
	 */
	public boolean lock(String key, String value) {
		if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
			// successfully lock
			return true;
		}

		String currentTime = stringRedisTemplate.opsForValue().get(key);
		if (!StringUtils.isEmpty(currentTime) && Long.parseLong(currentTime) < System.currentTimeMillis()) {
			// 查看是否先获取钥匙
			String oldTime = stringRedisTemplate.opsForValue().getAndSet(key, value);
			if (!StringUtils.isEmpty(oldTime) && oldTime.equals(currentTime)) {
				return true;
			}
		}

		return false;
	}


	/**
	 * 
	 * unlock function
	 * @param key
	 * @param value
	 * @throws
	 */
	public void unlock(String key, String value) {
		// securely unlock
		try {
			String currentTime = stringRedisTemplate.opsForValue().get(key);
			if (!StringUtils.isEmpty(currentTime) && currentTime.equals(value)) {
				stringRedisTemplate.opsForValue().getOperations().delete(key);
			}
		} catch (Exception e) {
			log.error("【redis distributed lock】 failed to unlock, {}", e);
		}
	}

}
