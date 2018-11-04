package com.hy.micro.service.dingding.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.enums.ProductStatus;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-02 
 * @github: https://github.com/weixuan2008
 */
public interface ProductInfoRepo extends JpaRepository<ProductInfo, String> {
	/**
	 * Query the product according to product status.
	 * @param productStatus
	 * @return
	 * @throws
	 */
	List<ProductInfo> findByProductStatus(ProductStatus productStatus);
	
	ProductInfo findByProductId(String productId);
}
