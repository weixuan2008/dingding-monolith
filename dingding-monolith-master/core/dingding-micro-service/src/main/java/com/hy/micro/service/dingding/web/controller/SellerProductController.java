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
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.enums.ProductStatus;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.ProductForm;
import com.hy.micro.service.dingding.service.ProductCategoryService;
import com.hy.micro.service.dingding.service.ProductService;
import com.hy.micro.service.dingding.utils.ProductIdSimpleUtils;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-17 
 * @github: https://github.com/weixuan2008
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
	private static final String defaultImageUrl = "/images/default.jpg";
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService categoryService;
	
	/**
	 * The api to get product list.
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			Map<String, Object> map) {
		
		PageRequest request = PageRequest.of(page - 1, size);
		Page<ProductInfo> productInfoPage = productService.findAll(request);
		
		map.put("productInfoPage", productInfoPage);
		map.put("currentPage", page);
		map.put("size", size);
		
		return new ModelAndView("product/list", map);
	}
	
	
	/**
	 * The api to online sale the product.
	 * @param productId
	 * @param map
	 * @return
	 * @throws
	 */
	@RequestMapping("/online_sale")
	public ModelAndView onlineSale(@RequestParam("productId") String productId,
			Map<String, Object> map) {
		
		try {
			productService.online(productId);
		}catch(BloomingException ex){
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/product/list");
			
			return new ModelAndView("common/error", map);
		}
		
		map.put("url", "/seller/product/list");
		return new ModelAndView("common/success", map);
	}
	
	
	/**
	 * The api to offline sale the product.
	 * @param productId
	 * @param map
	 * @return
	 * @throws
	 */
	@RequestMapping("/offline_sale")
	public ModelAndView offlineSale(@RequestParam("productId") String productId,
            Map<String, Object> map) {
		
		try {
			productService.offline(productId);
        } catch (BloomingException ex) {
            map.put("msg", ex.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);
	}
	
	
	/**
	 * The api to default index page.
	 * @return
	 * @throws
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
            Map<String, Object> map) {
		if(!StringUtils.isEmpty(productId)) {
			ProductInfo productInfo = productService.findByProductId(productId);
			map.put("productInfo", productInfo);
		}
		
		// Query all the product category
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        map.put("defaultImage", defaultImageUrl);

        return new ModelAndView("product/index", map);
	}
	
	/**
	 * The api to save or update product.
	 * @return
	 * @throws
	 */
	@RequestMapping("/save")
	public ModelAndView save(@Valid ProductForm form,
			BindingResult bindingResult,
			Map<String, Object> map) {
		
		if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            // if productId is null, that means to add a new product.
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findByProductId(form.getProductId());
            } else {
                form.setProductId(ProductIdSimpleUtils.getProductIdByUUID());
                productInfo.setProductStatus(ProductStatus.PRODUCT_NEW);
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (BloomingException ex) {
            map.put("msg", ex.getMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);
    }

}
