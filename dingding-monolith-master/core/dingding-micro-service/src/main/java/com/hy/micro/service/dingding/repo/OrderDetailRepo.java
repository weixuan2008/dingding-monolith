package com.hy.micro.service.dingding.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hy.micro.service.dingding.entity.OrderDetail;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-02
 * @github: https://github.com/weixuan2008
 */
public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {
	/**
	 * Get order details according order id.
	 * @param orderId
	 * @return
	 * @throws
	 */
	public List<OrderDetail> findByOrderId(String orderId);
}
