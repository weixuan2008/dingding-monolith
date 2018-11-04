package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The gender for user
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum GenderType implements CodeEnum<Integer> {
	//@formatter:off
	GENDER_FEMALE(0 , "Female"),
	GENDER_MALE(1 , "Male"),
	GENDER_UNKNOWN(2 , "Unknown")
    ;
	//@formatter:on

	private Integer code;
	private String content;

	private GenderType(Integer code, String content) {
		this.code = code;
		this.content = content;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
