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
import com.hy.micro.service.dingding.entity.ProductCategory;
import com.hy.micro.service.dingding.entity.Vendor;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.VendorForm;
import com.hy.micro.service.dingding.service.ProductCategoryService;
import com.hy.micro.service.dingding.service.VendorService;
import com.hy.micro.service.dingding.utils.PhoneNumberGenUtils;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */

@Controller
@RequestMapping("/seller/vendor")
public class SellerVendorController {
	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductCategoryService categoryService;

	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {

		PageRequest request = PageRequest.of(page - 1, size);
		Page<Vendor> vendorPage = vendorService.findAllVendors(request);

		map.put("vendorPage", vendorPage);
		map.put("currentPage", page);
		map.put("size", size);

		return new ModelAndView("vendor/list", map);
	}

	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "vendorId", required = false) String vendorId,
			Map<String, Object> map) {
		if (!StringUtils.isEmpty(vendorId)) {
			Vendor vendor = vendorService.findByVendorId(vendorId);
			map.put("vendor", vendor);
		}

		// Query all the product category
		List<ProductCategory> categoryList = categoryService.findAll();
		map.put("categoryList", categoryList);

		return new ModelAndView("vendor/index", map);
	}

	@RequestMapping("/save")
	public ModelAndView save(@Valid VendorForm form, BindingResult bindingResult, Map<String, Object> map) {

		if (bindingResult.hasErrors()) {
			map.put("msg", bindingResult.getFieldError().getDefaultMessage());
			map.put("url", "/seller/vendor/index");
			return new ModelAndView("common/error", map);
		}

		Vendor vendor = new Vendor();
		try {
			// if vendor is null, that means to add a new vendor.
			if (!StringUtils.isEmpty(form.getVendorId())) {
				vendor = vendorService.findByVendorId(form.getVendorId());
			} else {
				String no = PhoneNumberGenUtils.getMobile();
				form.setPhoneNumber(no);
				form.setVendorId(no);
			}
			BeanUtils.copyProperties(form, vendor);
			vendorService.save(vendor);
		} catch (BloomingException ex) {
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/vendor/index");
			return new ModelAndView("common/error", map);
		}

		map.put("url", "/seller/vendor/list");
		return new ModelAndView("common/success", map);
	}

}
