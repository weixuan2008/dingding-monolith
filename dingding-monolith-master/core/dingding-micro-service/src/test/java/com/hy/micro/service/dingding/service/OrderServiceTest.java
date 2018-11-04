package com.hy.micro.service.dingding.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.entity.OrderDetail;
import com.hy.micro.service.dingding.enums.OrderStatus;
import com.hy.micro.service.dingding.enums.PayStatus;
import com.hy.micro.service.dingding.service.impl.OrderServiceImpl;
import com.hy.micro.service.dingding.utils.PhoneNumberGenUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {
	@Autowired
	private OrderServiceImpl orderService;

	private final static String BUYER_OPENID = "8804266666";
	private final static String ORDER_ID = "20181009150138804266666";
	private final static String BUYER_ID = "360428197006156666";
	private final static String PRODUCT_ID_1 = "200595696666";
	private final static String PRODUCT_ID_2 = "200595698888";

	@Test
	public void createOrderTest() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = new OrderDTO();

		//orderDTO.setOrderId(OrderIdSimpleUtils.generateOrderId());
		orderDTO.setOrderId("20181009150138804266666");

		orderDTO.setBuyerId(BUYER_ID);
		orderDTO.setBuyerName("Eddie Wei");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		orderDTO.setBuyerPhone(PhoneNumberGenUtils.getMobile());
		orderDTO.setBuyerAddress("HePing Street District No.12");

		orderDTO.setOrderAmount(new BigDecimal(35.5));
		orderDTO.setOrderStatus(OrderStatus.ORDER_NEW);
		orderDTO.setPayStatus(PayStatus.PAYMENT_NEW);

		// shopping cart
		List<OrderDetail> orderDetailList = new ArrayList<>();
		OrderDetail od1 = new OrderDetail();
		od1.setProductId(PRODUCT_ID_1);
		od1.setProductQuantity(2);

		OrderDetail od2 = new OrderDetail();
		od2.setProductId(PRODUCT_ID_2);
		od2.setProductQuantity(5);

		orderDetailList.add(od1);
		orderDetailList.add(od2);

		orderDTO.setOrderDetailList(orderDetailList);

		OrderDTO result = orderService.create(orderDTO);
		log.info("[Create Order] result = {}", result);

		Assert.assertNotNull(result);
	}
	

	@Test
	public void fineOrderByIdTest() throws IllegalAccessException, InvocationTargetException {
		OrderDTO result = orderService.findByOrderId(ORDER_ID);
		log.info("[Query Order] result = {}", result);
		Assert.assertEquals(ORDER_ID, result.getOrderId());
	}

	@Test
	public void findListTest() {
		PageRequest request = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findAllByBuyerId(BUYER_ID, request);

		Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
	}

	@Test
	public void cancleOrderTest() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);
		OrderDTO result = orderService.cancel(orderDTO);

		Assert.assertEquals(OrderStatus.ORDER_CANCEL, result.getOrderStatus());
	}

	@Test
	public void finishOrderTest() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);
		OrderDTO result = orderService.finish(orderDTO);

		Assert.assertEquals(OrderStatus.ORDER_FINISH, result.getOrderStatus());
	}

	@Test
	public void payOrderTest() throws IllegalAccessException, InvocationTargetException {
		OrderDTO orderDTO = orderService.findByOrderId(ORDER_ID);
		OrderDTO result = orderService.pay(orderDTO);

		Assert.assertEquals(PayStatus.PAYMENT_COMPLETED, result.getOrderStatus());
	}

	@Test
	public void list() {
		PageRequest request = PageRequest.of(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findAll(request);

		Assert.assertTrue("Query all orders", orderDTOPage.getTotalElements() > 0);

	}

}
