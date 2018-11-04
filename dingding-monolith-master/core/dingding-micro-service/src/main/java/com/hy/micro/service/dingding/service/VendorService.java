package com.hy.micro.service.dingding.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hy.micro.service.dingding.entity.Vendor;

public interface VendorService {
	Vendor save(Vendor inventoryVendor);
	
	List<Vendor> findAllVendors();
	
	Vendor findByVendorId(String vendorId);
	
	Page<Vendor> findAllVendors(Pageable pageable);
}
