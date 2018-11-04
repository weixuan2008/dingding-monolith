package com.hy.micro.service.dingding.repo;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hy.micro.service.dingding.DingdingServiceTest;
import com.hy.micro.service.dingding.entity.OrderMaster;
import com.hy.micro.service.dingding.enums.OrderStatus;
import com.hy.micro.service.dingding.enums.PayStatus;
import com.hy.micro.service.dingding.repo.OrderMasterRepo;
import com.hy.micro.service.dingding.utils.IdCardGenUtils;
import com.hy.micro.service.dingding.utils.OrderIdSimpleUtils;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-06
 * @github: https://github.com/weixuan2008
 */

@Component
public class OrderMasterRepoTest extends DingdingServiceTest{
	private final static String orderId = "20181009150138804266666";
	
	@Autowired
	private OrderMasterRepo orderMasterRepository;

	@Test
	public void saveOneTest() {
		OrderMaster orderMaster = new OrderMaster();

		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(new BigDecimal(55.5));
		orderMaster.setOrderStatus(OrderStatus.ORDER_NEW);
		orderMaster.setPayStatus(PayStatus.PAYMENT_NEW);
		orderMaster.setBuyerId("360428197006156666");

		OrderMaster result = orderMasterRepository.save(orderMaster);

		Assert.assertNotEquals(null, result);
	}

	@Test
	public void saveMultiTest() {
		for (int i = 0; i < 2; i++) {
			OrderMaster orderMaster = new OrderMaster();

			orderMaster.setOrderId(OrderIdSimpleUtils.generateOrderId());
			orderMaster.setOrderAmount(new BigDecimal(new Random().nextDouble() * 5 + 50));
			orderMaster.setOrderStatus(OrderStatus.ORDER_NEW);
			orderMaster.setPayStatus(PayStatus.PAYMENT_NEW);
			orderMaster.setBuyerId(IdCardGenUtils.generateID());

			OrderMaster result = orderMasterRepository.save(orderMaster);

			Assert.assertNotEquals(null, result);
		}
	}
	
	@Test
	public void getOneTest() {
		OrderMaster orderMastr = orderMasterRepository.getOne(orderId);
		Assert.assertNotNull(orderMastr);
	}
	
	@Test
	public void findByOrderIdTest() {
		OrderMaster orderMastr = orderMasterRepository.findByOrderId(orderId);
		Assert.assertNotNull(orderMastr);
	}
}
