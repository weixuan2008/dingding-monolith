package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The code for result
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum ResultCode {
	//@formatter:off
	RESULT_SUCESS(0 , "Result Success"),
	RESULT_FAILURE(1 , "Result Failure"),
	LOGIN_SUCCESS(2, "Login Success"),
	LOGIN_FAILURE(3, "Login Failure"),
	LOGOUT_SUCCESS(4, "Logout Success"),
	LOGOUT_FAILURE(5, "Logout Failure"),
	;
	//@formatter:on

	private Integer code;
	private String msg;

	private ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
