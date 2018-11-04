package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;

import com.hy.micro.service.dingding.dto.OrderDTO;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-12
 * @github: https://github.com/weixuan2008
 */

public interface BuyerService {
	/** query one order by buyerId and orderId 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO findOrderOne(String buyerId, String orderId) throws IllegalAccessException, InvocationTargetException;

	/** cancel one order by buyerId and orderId 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO cancelOrder(String buyerId, String orderId) throws IllegalAccessException, InvocationTargetException;
}
