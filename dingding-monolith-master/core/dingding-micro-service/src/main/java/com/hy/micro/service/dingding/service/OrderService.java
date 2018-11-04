package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hy.micro.service.dingding.dto.OrderDTO;

/**
 * The business logic for order service.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-06
 * @github: https://github.com/weixuan2008
 */
public interface OrderService {
	/** Create order 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO create(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException;

	/** find one order by id 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO findByOrderId(String orderId) throws IllegalAccessException, InvocationTargetException;

	/** find all order by id **/
	Page<OrderDTO> findAllByBuyerId(String buyerId, Pageable pageable);

	/** cancel order 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO cancel(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException;

	/** finish an order 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO finish(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException;

	/** pay an order 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException **/
	OrderDTO pay(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException;

	/** find all orders **/
	Page<OrderDTO> findAll(Pageable pageable);
}
