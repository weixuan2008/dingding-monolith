package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hy.micro.service.dingding.dto.OrderDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PayServiceTest {
	private final static String ORDER_ID = "20181009150138804266666";
	
	@Autowired
	private PayService payService;

	@Autowired
	private OrderService orderService;

	@Test
	public void create() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);

		payService.create(orderDTO);
	}

	@Test
	public void refund() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);

		payService.refund(orderDTO);
	}

}
