package com.hy.micro.service.dingding.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.hy.micro.service.dingding.entity.OrderDetail;
import com.hy.micro.service.dingding.enums.OrderStatus;
import com.hy.micro.service.dingding.enums.PayStatus;

import lombok.Data;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
@Data
public class OrderDTO {
	/** order id **/
	private String orderId;

	/** buyer id **/
	private String buyerId;
	
	/** buyer name **/
	private String buyerName;

	/** buyer phone number **/
	private String buyerPhone;

	/** buyer addresss **/
	private String buyerAddress;

	/** buyer weixin id **/
	private String buyerOpenid;

	/** total amount for order **/
	private BigDecimal orderAmount;

	/** order status **/
	private OrderStatus orderStatus;

	/** pay status **/
	private PayStatus payStatus;

	/** create time **/
	private Date createTime;

	/** update time **/
	private Date updateTime;

	/** order detail list **/
	private List<OrderDetail> orderDetailList;

}
