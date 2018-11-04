package com.hy.micro.service.dingding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hy.micro.service.dingding.entity.Vendor;
import com.hy.micro.service.dingding.repo.VendorRepo;
import com.hy.micro.service.dingding.service.VendorService;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-23
 * @github: https://github.com/weixuan2008
 */
@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepo inventoryVendorRepository;

	@Override
	@Transactional
	public Vendor save(Vendor inventoryVendor) {
		return inventoryVendorRepository.save(inventoryVendor);
	}

	@Override
	public List<Vendor> findAllVendors() {
		return inventoryVendorRepository.findAll();
	}

	@Override
	public Vendor findByVendorId(String vendorId) {
		return inventoryVendorRepository.findByVendorId(vendorId);
	}

	@Override
	public Page<Vendor> findAllVendors(Pageable pageable) {
		return inventoryVendorRepository.findAll(pageable);
	}
}
