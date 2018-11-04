package com.hy.micro.service.dingding.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hy.micro.service.dingding.entity.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, String>{	
	Inventory findByProductId(String productId);
	
	List<Inventory> findByVendorId(String vendorId);
	
	List<Inventory> findByProductCategory(String category);
}
