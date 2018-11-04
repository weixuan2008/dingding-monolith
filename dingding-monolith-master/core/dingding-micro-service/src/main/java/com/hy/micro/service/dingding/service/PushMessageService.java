package com.hy.micro.service.dingding.service;

import com.hy.micro.service.dingding.dto.OrderDTO;

/**
 * push the message when order status changed.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface PushMessageService {
	/** make notification when changed the order status **/
	void notifyWhenOrderStatusChanged(OrderDTO orderDTO);
}
