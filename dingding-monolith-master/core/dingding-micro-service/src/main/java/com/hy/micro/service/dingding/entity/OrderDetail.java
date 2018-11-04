package com.hy.micro.service.dingding.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The detail information table for order.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="order_detail")
public class OrderDetail extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="detail_id")
	private String detailId;

	@Column(name="order_id", nullable=false)
	private String orderId;
	
	@Column(name="product_id", nullable=false)
	private String productId;

	/** unit price, and unit is fen **/
	@Column(name="product_price", nullable=false)
	private BigDecimal productPrice;

	@Column(name="product_quantity", nullable=false)
	private Integer productQuantity;

	/** static resource from CDN or file server **/
	@Column(name="product_icon")
	private String productIcon;
}
