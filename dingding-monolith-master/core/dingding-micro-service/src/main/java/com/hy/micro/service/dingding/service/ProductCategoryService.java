package com.hy.micro.service.dingding.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hy.micro.service.dingding.entity.ProductCategory;

/**
 * The category service for products.
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface ProductCategoryService {
	ProductCategory findById(Integer categoryId);

	Page<ProductCategory> findAll(Pageable pageable);
	
	List<ProductCategory> findAll();

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

	ProductCategory save(ProductCategory productCategory);
}
