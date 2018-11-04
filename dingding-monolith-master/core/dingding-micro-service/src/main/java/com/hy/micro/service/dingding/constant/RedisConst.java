package com.hy.micro.service.dingding.constant;

/**
 * redis constant
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */
public interface RedisConst {
	/** key prefix **/
	String REDIS_PREFIX = "token_%s";

	/** expire time(s) **/
	Integer REDIS_EXPIRE = 7200;
}
