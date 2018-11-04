package com.hy.micro.service.dingding.dto;

import lombok.Data;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
@Data
public class CartDTO {
	/** product id **/
	private String productId;

	/** product quantity **/
	private Integer productQuantity;

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
