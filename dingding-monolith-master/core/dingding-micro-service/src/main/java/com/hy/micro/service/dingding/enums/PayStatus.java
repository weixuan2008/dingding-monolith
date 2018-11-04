package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The status for payment
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum PayStatus implements CodeEnum<Integer> {
	//@formatter:off
	PAYMENT_NEW(0 , "New"),
	PAYMENT_COMPLETED(1 , "Completed"),
	PAYMENT_TIMEOUT(2 , "Timeout")
    ;
	//@formatter:on

	private Integer code;
	private String msg;

	private PayStatus(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
