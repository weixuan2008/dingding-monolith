package com.hy.micro.service.dingding.repo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hy.micro.service.dingding.DingdingServiceTest;
import com.hy.micro.service.dingding.entity.OrderDetail;
import com.hy.micro.service.dingding.repo.OrderDetailRepo;
import com.hy.micro.service.dingding.utils.OrderIdUtils;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-06
 * @github: https://github.com/weixuan2008
 */

@Component
public class OrderDetailRepoTest extends DingdingServiceTest{
	@Autowired
	private OrderDetailRepo orderDetailRepository;

	@Autowired
	private OrderIdUtils orderIdUtil;

	@Test
	public void saveOneTest() {
		OrderDetail orderDetail = new OrderDetail();

		orderDetail.setDetailId("499258418910636666");
		orderDetail.setOrderId("20181009150138804266666");
		orderDetail.setProductId("200595686666");
		orderDetail.setProductPrice(new BigDecimal(25.5));
		orderDetail.setProductQuantity(15);
		orderDetail.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");

		OrderDetail result = orderDetailRepository.save(orderDetail);

		Assert.assertNotEquals(null, result);
	}

	@Test
	public void saveMultiTest() {
		for (int i = 0; i < 2; i++) {
			OrderDetail orderDetail = new OrderDetail();

			orderDetail.setDetailId(Long.toString(orderIdUtil.nextId()));
			orderDetail.setOrderId("20181009150138804266666");
			orderDetail.setProductId("200595683826");
			orderDetail.setProductPrice(new BigDecimal(25.5));
			orderDetail.setProductQuantity(15);
			orderDetail.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");

			OrderDetail result = orderDetailRepository.save(orderDetail);

			Assert.assertNotEquals(null, result);
		}
	}

	@Test
	public void findByOrderIdTest() {
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("20181009150138804266666");

		Assert.assertNotEquals(0, orderDetailList.size());

	}

}
