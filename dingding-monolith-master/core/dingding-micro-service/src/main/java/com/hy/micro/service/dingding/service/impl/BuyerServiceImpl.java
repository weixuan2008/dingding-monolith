package com.hy.micro.service.dingding.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.service.BuyerService;
import com.hy.micro.service.dingding.service.OrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-12
 * @github: https://github.com/weixuan2008
 */

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
	@Autowired
	private OrderService orderService;

	@Override
	public OrderDTO findOrderOne(String buyerId, String orderId) throws IllegalAccessException, InvocationTargetException {
		return queryOrderByOwnerId(buyerId, orderId);
	}

	private OrderDTO queryOrderByOwnerId(String buyerId, String orderId) throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(orderId);
		if(orderDTO == null) {
			return null;
		}
		
		// Check owner of orderId
		if(!orderDTO.getBuyerId().equalsIgnoreCase(buyerId)) {
			log.error("Failed to check owner {} of order {}", buyerId, orderId);
			throw new BloomingException(ExceptionCode.ORDER_OWNER_NOT_MATCH);
		}
		
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO cancelOrder(String buyerId, String orderId) throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = queryOrderByOwnerId(buyerId, orderId);
		if (orderDTO == null) {
			log.error("Failed to cancel order because cannot find order {}", orderId);
			throw new BloomingException(ExceptionCode.ORDER_NOT_FOUND);
		}

		return orderService.cancel(orderDTO);
	}

}
