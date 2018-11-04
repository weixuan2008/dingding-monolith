package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The type for product category
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */
@Getter
public enum ProductCategoryType implements CodeEnum<Integer> {
	//@formatter:off
	CATEGORY_CHILDREN_EDUCATION(0 , "Children Education"),
	CATEGORY_SCIENCE_TECHNOLOGY(1 , "Science and Technology"),
	CATEGORY_INFORMATION_TECHNOLOGY(2 , "Information Technology"),
	CATEGORY_LITERATURE_ART(3, "Literature and Art"),
	CATEGORY_ECONOMICS_MANAGEMENT(4, "Economics and Management"),
	CATEGORY_LIFE_LEISURE(5, "Life and Leisure"),
	CATEGORY_OFFICE_PRODUCTS(6, "Office Products")
    ;
	//@formatter:on

	private Integer code;
	private String msg;

	private ProductCategoryType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

}
