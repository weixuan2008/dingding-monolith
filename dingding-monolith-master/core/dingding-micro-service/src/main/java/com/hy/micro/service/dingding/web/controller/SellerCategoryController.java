package com.hy.micro.service.dingding.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.CategoryForm;
import com.hy.micro.service.dingding.service.ProductCategoryService;

/**
 * The product category for vendor.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
	@Autowired
	private ProductCategoryService categoryService;

	/**
	 * category list for products.
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			Map<String, Object> map) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<ProductCategory> productCategoryPage = categoryService.findAll(request);
		
		map.put("productCategoryPage", productCategoryPage);
		map.put("currentPage", page);
		map.put("size", size);
		
		return new ModelAndView("category/list", map);
	}

	/**
	 * index page for default
	 * 
	 * @param categoryId
	 * @param map
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
			Map<String, Object> map) {
		if (categoryId != null) {
			ProductCategory productCategory = categoryService.findById(categoryId);
			map.put("category", productCategory);
		}

		return new ModelAndView("category/index", map);
	}

	/**
	 * save or update.
	 * 
	 * @param form
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult, Map<String, Object> map) {
		if (bindingResult.hasErrors()) {
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error", map);
		}

		ProductCategory productCategory = new ProductCategory();
		try {
			if (form.getCategoryId() != null) {
				productCategory = categoryService.findById(form.getCategoryId());
			}
			BeanUtils.copyProperties(form, productCategory);
			categoryService.save(productCategory);
		} catch (BloomingException ex) {
			map.put("msg", ex.getMessage());
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error", map);
		}

		map.put("url", "/seller/category/list");
		return new ModelAndView("common/success", map);
	}
}
