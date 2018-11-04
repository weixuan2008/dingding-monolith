package com.hy.micro.service.dingding.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hy.micro.service.dingding.dto.CartDTO;
import com.hy.micro.service.dingding.entity.Inventory;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-23 
 * @github: https://github.com/weixuan2008
 */
public interface InventoryService {
	Inventory findByProductId(String productId);
	
	Inventory save(Inventory inventoryMaster);
	
	void decreaseSku(List<CartDTO> cartDTOList);

	void increaseSku(List<CartDTO> cartDTOList);

	List<Inventory> findAll();
	
	Page<Inventory> findAll(Pageable pageable);
}
