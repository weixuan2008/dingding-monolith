package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The status for product
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum ProductStatus implements CodeEnum<Integer> {
	//@formatter:off
	PRODUCT_NEW(0 , "New"),
	PRODUCT_ONLINE(1 , "Online"),
	PRODUCT_OFFLINE(2 , "Offline"),
	PRODUCT_SELLOUT(3 , "Sellout"),
    ;
	//@formatter:on

	private Integer code;
	private String msg;

	private ProductStatus(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
