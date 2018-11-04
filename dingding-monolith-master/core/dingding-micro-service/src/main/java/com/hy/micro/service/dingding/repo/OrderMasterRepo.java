package com.hy.micro.service.dingding.repo;

import com.hy.micro.service.dingding.entity.OrderMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-01 
 * @github: https://github.com/weixuan2008
 */
public interface OrderMasterRepo extends JpaRepository<OrderMaster, String>{
	
	/**
	 * Get all orders according to buyer id.
	 * @param buyerId
	 * @param pageable
	 * @return 
	 * @throws
	 */
	Page<OrderMaster> findByBuyerId(String buyerId, Pageable pageable);
	
	OrderMaster findByOrderId(String orderId);
}
