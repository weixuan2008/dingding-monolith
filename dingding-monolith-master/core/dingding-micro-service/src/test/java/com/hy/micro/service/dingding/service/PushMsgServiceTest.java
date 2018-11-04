package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.service.impl.PushMessageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PushMsgServiceTest {
	private final static String ORDER_ID = "20181009150138804266666";
	
	@Autowired
	private PushMessageServiceImpl pushMessageService;

	@Autowired
	private OrderService orderService;

	@Test
	public void notifyByOrderId() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);
		pushMessageService.notifyWhenOrderStatusChanged(orderDTO);
	}

}
