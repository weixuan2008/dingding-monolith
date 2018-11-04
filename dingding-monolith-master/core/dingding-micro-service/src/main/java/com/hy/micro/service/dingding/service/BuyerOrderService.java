package com.hy.micro.service.dingding.service;

import com.hy.micro.service.dingding.dto.OrderDTO;

/**
 * business logic for order of buyer
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface BuyerOrderService {
	/** find order by buyer id and order id **/
	OrderDTO findOne(String buyerId, String orderId);

	/** cancel order by buyer id and order id **/
	OrderDTO cancel(String buyerId, String orderId);
}
