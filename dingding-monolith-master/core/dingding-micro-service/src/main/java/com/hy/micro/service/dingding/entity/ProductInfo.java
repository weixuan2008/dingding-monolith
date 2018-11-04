package com.hy.micro.service.dingding.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hy.micro.service.dingding.enums.ProductStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The detail information table for product.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_info")
public class ProductInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	private String productId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	/** product price, unit is fen **/
	@Column(name = "product_price", nullable = false)
	private BigDecimal productPrice;

	@Column(name = "product_stock", nullable = false)
	private int productStock;

	@Column(name = "product_desc")
	private String productDesc;

	/** static resource from CDN or file server **/
	@Column(name = "product_icon")
	private String productIcon;

	@Column(name = "category_type", nullable = false)
	private Integer categoryType;

	@Column(name = "product_status", nullable = false)
	private ProductStatus productStatus;
}
