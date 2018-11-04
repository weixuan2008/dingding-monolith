package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;

import com.hy.micro.service.dingding.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface PayService {
	/** create an order **/
	PayResponse create(OrderDTO orderDTO);

	/** handle sync notification 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	PayResponse notify(String notifyData) throws IllegalAccessException, InvocationTargetException;

	/** refund when canceled order **/
	RefundResponse refund(OrderDTO orderDTO);
}
