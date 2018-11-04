package com.hy.micro.service.dingding.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.hy.micro.service.dingding.enums.OrderStatus;
import com.hy.micro.service.dingding.enums.PayStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The master table for order.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="order_master")
public class OrderMaster extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "order_id")
	private String orderId;

	@Column(name = "buyer_id", nullable = false)
	private String buyerId;
	
	/* total amount for order, unit is fen */
	@Column(name = "order_amount", nullable = false)
	private BigDecimal orderAmount;

	/** order status **/
	@Column(name = "order_status", nullable = false)
	private OrderStatus orderStatus;
	
	/** pay status **/
	@Column(name = "pay_status", nullable = false)
	private PayStatus payStatus;
}
