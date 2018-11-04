package com.hy.micro.service.dingding.repo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hy.micro.service.dingding.DingdingServiceTest;
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.repo.ProductCategoryRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-05
 * @github: https://github.com/weixuan2008
 */

@Slf4j
@Component
public class ProductCategoryRepoTest extends DingdingServiceTest{
	@Autowired
	private ProductCategoryRepo productCategoryRepository;

	@Test
	// @Transactional
	public void saveOneTest() {
		ProductCategory productCategory = new ProductCategory();

		productCategory.setCategoryName("Children books");
		productCategory.setCategoryType(100);

		ProductCategory result = productCategoryRepository.save(productCategory);
		Assert.assertNotEquals(null, result);
	}

	@Test
	// @Transactional
	public void saveMultiTest() {
		for (int i = 0; i < 5; i++) {
			ProductCategory productCategory = new ProductCategory();

			productCategory.setCategoryName("Children books");
			productCategory.setCategoryType(200);

			ProductCategory result = productCategoryRepository.save(productCategory);
			Assert.assertNotEquals(null, result);
		}
	}

	@Test
	public void findTest() {
		Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);

		log.info(productCategory.get().toString());

		Assert.assertNotEquals(null, productCategory.get());
	}

	@Test
	public void findAllTest() {
		List<ProductCategory> productCategory = productCategoryRepository.findAll();

		log.info(productCategory.toString());

		Assert.assertNotEquals(null, productCategory);
	}

	@Test
	public void updateTest() {
		ProductCategory productCategory = productCategoryRepository.findById(1).get();

		productCategory.setCategoryType(300);
		;
		productCategory.setCategoryName("Children books");

		productCategoryRepository.save(productCategory);
	}

	@Test
	public void findByCategoryTypeInTest() {
		List<Integer> categoryType = Arrays.asList(100, 200);

		List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryType);

		Assert.assertNotEquals(0, result.size());
	}
}
