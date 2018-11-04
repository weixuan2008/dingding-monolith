package com.hy.micro.service.dingding.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hy.micro.service.dingding.entity.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, String>{
	Vendor findByVendorId(String vendorId);

}
