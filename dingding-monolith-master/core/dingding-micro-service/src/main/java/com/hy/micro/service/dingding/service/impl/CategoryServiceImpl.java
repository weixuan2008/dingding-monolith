package com.hy.micro.service.dingding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.repo.ProductCategoryRepo;
import com.hy.micro.service.dingding.service.ProductCategoryService;

/**
 * The implementation for category service.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-12
 * @github: https://github.com/weixuan2008
 */

@Service
public class CategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryRepo categoryRepository;

	@Override
	public ProductCategory findById(Integer categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public Page<ProductCategory> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
		return categoryRepository.findByCategoryTypeIn(categoryTypes);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return categoryRepository.save(productCategory);
	}

	@Override
	public List<ProductCategory> findAll() {
		return categoryRepository.findAll();
	}
}
