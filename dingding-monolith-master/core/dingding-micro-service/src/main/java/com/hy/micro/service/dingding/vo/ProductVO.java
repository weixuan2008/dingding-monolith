package com.hy.micro.service.dingding.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The product detail that will return to front-end, includes product category.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-09
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	@JsonProperty("name")
	private String categoryName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("products")
	private List<ProductInfoVO> productInfos;
}
