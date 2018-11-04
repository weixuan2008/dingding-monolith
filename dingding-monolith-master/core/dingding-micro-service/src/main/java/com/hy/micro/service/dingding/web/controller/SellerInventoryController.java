package com.hy.micro.service.dingding.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hy.micro.service.dingding.entity.Inventory;
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.entity.Vendor;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.InventoryForm;
import com.hy.micro.service.dingding.service.InventoryService;
import com.hy.micro.service.dingding.service.ProductCategoryService;
import com.hy.micro.service.dingding.service.VendorService;
import com.hy.micro.service.dingding.utils.ProductIdSimpleUtils;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */

@Controller
@RequestMapping("/seller/inventory")
public class SellerInventoryController {
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductCategoryService categoryService;
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {

		PageRequest request = PageRequest.of(page - 1, size);
		Page<Inventory> inventoryPage = inventoryService.findAll(request);

		map.put("inventoryPage", inventoryPage);
		map.put("currentPage", page);
		map.put("size", size);

		return new ModelAndView("inventory/list", map);
	}

	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
			Map<String, Object> map) {
		if (!StringUtils.isEmpty(productId)) {
			Inventory inventory = inventoryService.findByProductId(productId);
			map.put("inventory", inventory);
		}

		// Query all the vendor
		List<Vendor> vendorList = vendorService.findAllVendors();
		map.put("vendorList", vendorList);

		// Query all the product category
		List<ProductCategory> categoryList = categoryService.findAll();
		map.put("categoryList", categoryList);
				
		return new ModelAndView("inventory/index", map);
	}

	@RequestMapping("/save")
	public ModelAndView save(@Valid InventoryForm form, BindingResult bindingResult, Map<String, Object> map) {

		if (bindingResult.hasErrors()) {
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/seller/inventory/index");
			return new ModelAndView("common/error", map);
		}

		Inventory inventory = new Inventory();
		try {
			// if productId is null, that means to add a new inventory.
			if (!StringUtils.isEmpty(form.getProductId())) {
				inventory = inventoryService.findByProductId(form.getProductId());
			} else {
				form.setProductId(ProductIdSimpleUtils.getProductIdByUUID());
			}
			BeanUtils.copyProperties(form, inventory);
			inventoryService.save(inventory);
		} catch (BloomingException ex) {
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/inventory/index");
			return new ModelAndView("common/error", map);
		}

		map.put("url", "/seller/inventory/list");
		return new ModelAndView("common/success", map);
	}

}
