package com.hy.micro.service.dingding.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.service.ProductCategoryService;
import com.hy.micro.service.dingding.service.ProductService;
import com.hy.micro.service.dingding.utils.ResultUtils;
import com.hy.micro.service.dingding.vo.ProductInfoVO;
import com.hy.micro.service.dingding.vo.ProductVO;
import com.hy.micro.service.dingding.vo.ResultVO;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService categoryService;

	@GetMapping("/list")
	public ResultVO<Object> list() {
		// 1. query all online products
		List<ProductInfo> productInfos = productService.findAllOnline();

		// 2. query all product category
		// The method-1
		// List<CategoryType> categoryTypeList = new ArrayList<>();
		// for (ProductInfo productInfo : productInfoList) {
		// categoryTypeList.add(productInfo.getCategoryType());
		// }

		// The method-2 using lambda.
		List<Integer> categoryTypeList = productInfos.stream().map(e -> e.getCategoryType())
				.collect(Collectors.toList());

		List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);

		// 3. To encapsulate the data
		List<ProductVO> productVOs = new ArrayList<ProductVO>();
		for (ProductCategory productCategory : productCategories) {
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());

			List<ProductInfoVO> productInfoVOList = new ArrayList<>();
			for (ProductInfo productInfo : productInfos) {
				if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfos(productInfoVOList);
			productVOs.add(productVO);
		}

		return ResultUtils.success(productVOs);
	}

}
