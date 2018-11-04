package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The status for order
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum OrderStatus implements CodeEnum<Integer> {
	//@formatter:off
	ORDER_NEW(0 , "New"),
	ORDER_WAIT(1 , "WaitingPay"),
	ORDER_PAYED(2 , "Payed"),
    ORDER_FINISH(3 , "Complated"),
    ORDER_CLOSED(4 , "Closed"),
    ORDER_CANCEL(5 , "Canceled"),
    ORDER_ABNORMAL(6 , "Abnormal")
    ;
	//@formatter:on

	private Integer code;
	private String msg;

	private OrderStatus(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
