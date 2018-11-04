package com.hy.micro.service.dingding.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.service.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCategoryServiceTest {
	@Autowired
	private CategoryServiceImpl categoryService;

	@Test
	public void findOne() {
		ProductCategory productCategory = categoryService.findById(1);
		Assert.assertNotEquals(null, productCategory);
	}

	@Test
	public void findAll() {
		List<ProductCategory> productCategorys = categoryService.findAll();
		Assert.assertNotEquals(0, productCategorys.size());
	}

	@Test
	public void findByCategoryTypeIn() {
		List<ProductCategory> productCategorys = categoryService.findByCategoryTypeIn(Arrays.asList(100, 200,300, 400));
		
		Assert.assertNotEquals(0, productCategorys.size());
	}

	@Test
	public void save() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("Office Word 2017");
		productCategory.setCategoryType(100);
		
		ProductCategory result = categoryService.save(productCategory);
		Assert.assertNotNull(result);
	}
}
