package com.hy.micro.service.dingding.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The product information that will return to front-end.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInfoVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String productId;

	@JsonProperty("name")
	private String productName;

	@JsonProperty("price")
	private BigDecimal productPrice;

	@JsonProperty("description")
	private String productDesc;

	@JsonProperty("icon")
	private String productIcon;
}
