package com.hy.micro.service.dingding.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hy.micro.service.dingding.dto.CartDTO;
import com.hy.micro.service.dingding.entity.Inventory;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.repo.InventoryRepo;
import com.hy.micro.service.dingding.service.InventoryService;

/**
 * The implementation for InventoryMaster service.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-23
 * @github: https://github.com/weixuan2008
 */

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryRepo inventoryMasterRepository;

	@Override
	public Inventory findByProductId(String productId) {
		return inventoryMasterRepository.findByProductId(productId);
	}

	@Override
	public List<Inventory> findAll() {
		return inventoryMasterRepository.findAll();
	}
	
	@Override
	public Page<Inventory> findAll(Pageable pageable) {
		return inventoryMasterRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Inventory save(Inventory inventoryMaster) {
		return inventoryMasterRepository.save(inventoryMaster);
	}

	@Override
	@Transactional
	public void decreaseSku(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			Inventory inventoryMaster = inventoryMasterRepository.findByProductId(cartDTO.getProductId());
			if (inventoryMaster == null) {
				throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
			}

			Integer result = inventoryMaster.getSku() - cartDTO.getProductQuantity();
			if (result < 0) {
				throw new BloomingException(ExceptionCode.PRODUCT_STOCK_ERROR);
			}

			inventoryMaster.setSku(result);

			inventoryMasterRepository.save(inventoryMaster);
		}
	}

	@Override
	@Transactional
	public void increaseSku(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			Inventory inventoryMaster = inventoryMasterRepository.findByProductId(cartDTO.getProductId());
			if (inventoryMaster == null) {
				throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
			}

			Integer result = inventoryMaster.getSku() + cartDTO.getProductQuantity();
			inventoryMaster.setSku(result);

			inventoryMasterRepository.save(inventoryMaster);
		}

	}
}
