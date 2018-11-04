package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The role for user
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum UserRole implements CodeEnum<Integer> {
	//@formatter:off
	ROLE_BUYER(0 , "Buyer"),
	ROLE_SELLER(1 , "Seller"),
	ROLE_NORMAL(2 , "Normal"),
	ROLE_ADMIN(99 , "Admin")
    ;
	//@formatter:on

	private Integer code;
	private String content;

	private UserRole(Integer code, String content) {
		this.code = code;
		this.content = content;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
