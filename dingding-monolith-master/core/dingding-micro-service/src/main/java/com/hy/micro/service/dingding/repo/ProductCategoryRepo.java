package com.hy.micro.service.dingding.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hy.micro.service.dingding.entity.ProductCategory;


/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-02 
 * @github: https://github.com/weixuan2008
 */
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {
	/**
	 * Get product category list according to gave category types.
	 * @param categoryTypes
	 * @return
	 * @throws
	 */
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
}
